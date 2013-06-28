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
use FindBin;
use lib $FindBin::Bin;
use files;

# Read a bunch of stats files, and summarize the times for a
# particluar function, based on the parameters.

my $function = "VHTI_generate_dictionary_secrets";
my $knob_of_interest = 'voters';

open (GRAPH, ">", "graph-me")
  or die "Can't open `graph-me' for writing: $!; stopped";

my $other_knobs;

foreach (glob ("stats-*Z")) {
  my $VAR1;
  eval (snarf ($_));
  my $seconds_per_call = ($VAR1->{stats}->{$function}->{seconds}
                          /
                          $VAR1->{stats}->{$function}->{calls});

  my @params;
  foreach (keys %{$VAR1->{parameters}}) {
    push @params, "$_: $VAR1->{parameters}->{$_}";
  }
  print GRAPH "$VAR1->{parameters}->{$knob_of_interest} $seconds_per_call\n";
  if (! defined ($other_knobs)) {
    foreach (@params) {
      $other_knobs->{$_}++;
    }
    delete $other_knobs->{$knob_of_interest};
  }
}

my $title = qq($function ) . join (',', sort keys %$other_knobs);

close (GRAPH)
  or warn "Can't close filehandle: $!";

0 == system (qq(graph -L "$title" --title-font-size=.0375 -X $knob_of_interest -Y "seconds/call" -S 2 -T png < graph-me > graph-me.png && cygstart graph-me.png))
  or warn "Couldn't run `graph' et al";