/*
 * JML specification Copyright 2004 SoS Group, University of Nijmegen
 */

package sos.koa;

import java.io.File;

/**
 * Global constants used in the user interface.
 *
 * @version $Id: KOAConstants.java,v 1.23 2004/06/08 13:06:50 hubbers Exp $
 *
 * @author Engelbert Hubbers (hubbers@cs.kun.nl)
 */
public interface KOAConstants {

   // Release info
   // Do not edit: generated by 'make distr'

   public static final String RELEASE = "v1.3";

   // Application state.

   public static final int INIT_STATE = 0;
   public static final int CLEARED_STATE = 1;
   public static final int CANDIDATES_IMPORTED_STATE = 2;
   public static final int VOTES_IMPORTED_STATE = 3;
   public static final int PRIVATE_KEY_IMPORTED_STATE = 4;
   public static final int PUBLIC_KEY_IMPORTED_STATE = 5;
   public static final int VOTES_DECRYPTED_STATE = 6;
   public static final int VOTES_COUNTED_STATE = 7;
   public static final int REPORT_GENERATED_STATE = 8;

   // Messages.

   static final String TITLE = "Kiezen Op Afstand (" + RELEASE + ")";

   public static final String RESTART_BUT_TXT = "Opnieuw Beginnen";
   public static final String CLEAR_BUT_TXT = "Wissen Gegevens";
   public static final String IMPORT_CANDIDATES_BUT_TXT = "Importeren Kandidatenbestand";
   public static final String IMPORT_VOTES_BUT_TXT = "Importeren Export Stembus";
   public static final String IMPORT_PRIVATE_KEY_BUT_TXT = "Importeren Private Sleutel";
   public static final String IMPORT_PUBLIC_KEY_BUT_TXT = "Importeren Publieke Sleutel";
   public static final String DECRYPT_BUT_TXT = "Ontsleutelen";
   public static final String COUNT_BUT_TXT = "Tellen";
   public static final String REPORT_BUT_TXT = "Rapporteren";
   public static final String HELP_BUT_TXT = "Help";
   public static final String EXIT_BUT_TXT = "Sluiten";

   static final String RESTART_TASK_MSG = "Opnieuw beginnen...";
   static final String RESTART_WARNING_MSG = "Opnieuw beginnen.\nZeker weten?";
   static final String RESTART_SUCCESS_MSG = "Opnieuw beginnen is gelukt!";
   static final String RESTART_FAILURE_MSG = "Opnieuw beginnen is mislukt!";

   static final String CLEAR_TASK_MSG = "Gegevens wissen...";
   static final String CLEAR_WARNING_MSG_1 = "Gegevens wissen.";
   static final String CLEAR_WARNING_MSG_2 = "Zeker weten?";
   static final String CLEAR_WARNING_MSG = CLEAR_WARNING_MSG_1 + "\n" +
                                           CLEAR_WARNING_MSG_2;
   static final String CLEAR_SUCCESS_MSG = "Wissen is gelukt!";
   static final String CLEAR_FAILURE_MSG = "Wissen is mislukt!";

   static final String IMPORT_CANDIDATES_TASK_MSG = "Kandidaten importeren...";
   static final String IMPORT_CANDIDATES_WARNING_MSG = "Kandidaten importeren.\nZeker weten?";
   static final String IMPORT_CANDIDATES_SUCCESS_MSG = "Importeren is gelukt!";
   static final String IMPORT_CANDIDATES_FAILURE_MSG = "Importeren is mislukt!";

   static final String IMPORT_VOTES_TASK_MSG = "Stemmen importeren...";
   static final String IMPORT_VOTES_WARNING_MSG = "Stemmen importeren.\nZeker weten?";
   static final String IMPORT_VOTES_SUCCESS_MSG = "Importeren is gelukt!";
   static final String IMPORT_VOTES_FAILURE_MSG = "Importeren is mislukt!";

   static final String IMPORT_PUBLIC_KEY_TASK_MSG = "Publieke sleutel importeren...";
   static final String IMPORT_PRIVATE_KEY_TASK_MSG = "Private sleutel importeren...";
   // static final String IMPORT_KEY_TASK_MSG = "Sleutel importeren...";
   static final String IMPORT_KEY_WARNING_MSG = "Sleutel importeren.\nZeker weten?";
   static final String IMPORT_KEY_SUCCESS_MSG = "Importeren is gelukt!";
   static final String IMPORT_KEY_FAILURE_MSG = "Importeren is mislukt!";
   
   static final String DECRYPT_TASK_MSG = "Stemmen ontsleutelen...";
   static final String DECRYPT_WARNING_MSG = "Stemmen ontsleutelen.\nZeker weten?";
   static final String DECRYPT_SUCCESS_MSG = "Ontsleutelen is gelukt!";
   static final String DECRYPT_FAILURE_MSG = "Ontsleutelen is mislukt!";
   static final String DECRYPT_ERROR_MSG = "Fout tijdens decryptie!";
   static final String DECRYPT_ERROR_TAG = "DECRYPTERROR";

   static final String COUNT_TASK_MSG = "Stemmen tellen...";
   static final String COUNT_WARNING_MSG = "Stemmen tellen.\nZeker weten?";
   static final String COUNT_SUCCESS_MSG = "Tellen is gelukt!";
   static final String COUNT_FAILURE_MSG = "Tellen is mislukt!";

   static final String REPORT_TASK_MSG = "Rapporteren...";
   static final String REPORT_WARNING_MSG = "Rapporteren.\nZeker weten?";
   static final String REPORT_SUCCESS_MSG = "Rapporteren is gelukt!";
   static final String REPORT_FAILURE_MSG = "Rapporteren is mislukt!";

   static final String HELP_TASK_MSG = "Help...";

   static final String EXIT_TASK_MSG = "Applicatie sluiten...";
   static final String EXIT_WARNING_MSG = "Applicatie sluiten.\nZeker weten?";
   static final String EXIT_SUCCESS_MSG = "Sluiten is gelukt!";
   static final String EXIT_FAILURE_MSG = "Sluiten is mislukt!";

   static final String DIALOG_ERROR_MSG = "Onbekende fout tijdens dialoog!";

   static final String TASK_CANCELED_MSG = "Taak geannuleerd!";
   static final String NO_ERRORS_MSG = "Geen fouten opgetreden!";
   static final String VOTES_IGNORED_SEE_MORE_INFO_MSG = " genegeerd, zie meer info!";

   static final String DEFAULT_VOTING_INTERVAL = "10 Mei 2004 9:00 - 10 Juni 2004 17:00";

   static final Object[] OK_OPTIONS = { "OK" };
   static final Object[] OK_CANCEL_OPTIONS = { "OK", "Annuleren" };
   static final Object[] YES_CANCEL_OPTIONS = { "Ja", "Annuleren" };
   static final Object[] YES_NO_OPTIONS = { "Ja", "Nee" };
   static final Object[] FORWARD_BACK_OPTIONS = { "Verder", "Terug" };
   static final Object[] OK_MOREINFO_OPTIONS = { "OK", "Meer Info" };
   static final Object[] OK_MOREINFO_CANCEL_OPTIONS = { "OK", "Meer Info", "Annuleren" };
   static final Object[] FORWARD_MOREINFO_BACK_OPTIONS = { "Verder", "Meer Info", "Terug" };
   static final Object[] OK_LESSINFO_OPTIONS = { "OK", "Minder Info" };
   static final Object[] OK_LESSINFO_CANCEL_OPTIONS = { "OK", "Minder Info", "Annuleren" };
   static final int MOREINFO_OPTION = 1;
   static final int LESSINFO_OPTION = 1;

   static final Object[] REPORT_OPTIONS = { "Verwerkingsverslag", "Resultaat stemming", "Annuleren" };
   static final int AUDITLOG = 0;
   static final int RECOUNT = 1;

   // Files

   static final String BASEDIR = ".";
   static final String OUTDIR = "tmp";

   static final String DECRYPTEDFILE = "decrypted.txt";

   static final String AUDITLOG_XSL = "xml/xslt/auditlog2pdf.xsl";
   static final String AUDITLOG_XML = "verwerkingsverslag.xml";
   static final String AUDITLOG_PDF = "verwerkingsverslag.pdf";

   static final String RECOUNT_XSL = "xml/xslt/recount2pdf.xsl";
   static final String RECOUNT_XML = "telling.xml";
   static final String RECOUNT_PDF = "telling.pdf";

   // Keytypes

   public static final int PUBLIC_KEYTYPE = 0;
   public static final int PRIVATE_KEYTYPE = 1;

   // Other constants
   public static final int NUMBER_OF_REDUNDANT_FIELDS = 7;
   public static final int MAX_KEY_LENGTH = 1024;
   public static final int MAX_ENCRYPTED_VOTE_LENGTH = 1024;
   public static final int ADDITIONAL_INFO_MAX_HEIGHT = 30;
   public static final int ADDITIONAL_INFO_MAX_WIDTH = 30;
   public static final int ADDITIONAL_INFO_EXTRA = 5;
   
}


