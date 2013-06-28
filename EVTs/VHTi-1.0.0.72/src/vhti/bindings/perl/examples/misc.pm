#!/usr/bin/perl
# 
# This material is subject to the VoteHere Source Code Evaluation
# License Agreement ("Agreement").  Possession and/or use of this
# material indicates your acceptance of this Agreement in its entirety.
# Copies of the Agreement may be found at www.votehere.net.
# 
# Copyright 2004 VoteHere, Inc.  All Rights Reserved
# 
# You may not download this Software if you are located in any country
# (or are a national of a country) subject to a general U.S. or
# U.N. embargo or are deemed to be a terrorist country (i.e., Cuba,
# Iran, Iraq, Libya, North Korea, Sudan and Syria) by the United States
# (each a "Prohibited Country") or are otherwise denied export
# privileges from the United States or Canada ("Denied Person").
# Further, you may not transfer or re-export the Software to any such
# country or Denied Person without a license or authorization from the
# U.S. government.  By downloading the Software, you represent and
# warrant that you are not a Denied Person, are not located in or a
# national of a Prohibited Country, and will not export or re-export to
# any Prohibited Country or Denied Party.

use warnings;
use strict;
use Data::Dumper;
use Math::BigInt lib => 'GMP';

require Exporter;
our @ISA = qw(Exporter);

our @EXPORT_OK = qw(xml_2_bignum fisher_yates_shuffle);

sub xml_2_bignum {
  my $tree = shift;
  my $encoding = $tree->attribute ("Encoding");
  if ("DEC" eq $encoding) {
    return new Math::BigInt ($tree->characters ());
  } elsif ("BASE64" eq $encoding) {
    use MIME::Base64;
    my $binary_data = decode_base64($tree->characters ());
    return new Math::BigInt ("0x" . unpack ("H*", $binary_data));
  } else {
    die "Unrecognized encoding: $encoding";
  }
}

sub fisher_yates_shuffle {
  my $array = shift;
  my $i;
  for ($i = @$array; --$i; ) {
    my $j = int rand ($i+1);
    next if $i == $j;
    @$array[$i,$j] = @$array[$j,$i];
  }
}
