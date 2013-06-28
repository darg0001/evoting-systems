/*
 * JML specification Copyright 2004 SoS Group, University of Nijmegen
 */

package sos.koa;

import java.io.File;
import java.io.IOException;

/**
 * A static class used to execute a native PDF viewer on a specific
 * PDF file generated by the KOA application.  one or more districts.
 *
 * <p> If the user wants to specify a particular PDF viewer, they should use
 * the PdfViewer property, e.g.,
 * <pre>
 *    java -DPdfViewer=my_viewer sos.koa.MenuPanel
 * </pre>
 * </p>
 *
 * @author Joe Kiniry (kiniry@cs.kun.nl)
 * @version $Id: ViewPdf.java,v 1.9 2004/05/04 19:51:52 martijno Exp $
 */

class ViewPdf
{
  /**
   * The command-line Java property that is used to determine a
   * user-defined PDF previewer.
   */
  private static final String PDF_VIEW_PROPERTY = "PdfViewer";

  /**
   * An ordered list of pre-defined PDF viewers.  The order is based
   * upon some combination of the capability and likelihood of each
   * viewer being installed.
   */
  private static final /*@ non_null @*/ String[] DEFAULT_PDF_VIEWERS = {
    "acroread", "acrobat",
    "GPdf", "kpdf", "Kghostview", "gv", "ggv", "gsview", "xpdf", "gs"
  };

  /**
   * This class should never be instantiated.
   */
  ViewPdf() {
    assert false;
  }

  /**
   * Display in a PDF viewer the specified file.  If the file does not
   * exist, is not readable, etc., then this method will simply return.
   *
   * @param a_pdf_file the file to show to the user.
   * @throws IOException if something goes seriously wrong while reading
   * the specified PDF file.
   */
  static void showPdf(final /*@ non_null @*/ File a_pdf_file)
    throws IOException {
    Process viewer_process;
    Runtime runtime = Runtime.getRuntime();
    String[] arguments = new String[] { null, a_pdf_file.getCanonicalPath() };

    String pdf_viewer = System.getProperty(PDF_VIEW_PROPERTY);

    // Try to use the user-specified PDF viewer first.
    if (pdf_viewer != null) {
      try {
        arguments[0] = pdf_viewer;
        viewer_process = runtime.exec(arguments);
        return;
      } catch (Exception e) {
        // skip and try next viewer
      }
    }
    // Check to see if we are running in OS X next.
    if (System.getProperty("os.name").startsWith("Mac OS")) {
      try {
        arguments[0] = "open";
        viewer_process = runtime.exec(arguments);
        return;
      } catch (Exception e) {
        // skip and try next viewer
      }
    }
    // Check to see if we are running in Windows.
    if (System.getProperty("os.name").startsWith("Windows")) {
      try {
        viewer_process = runtime.exec("cmd /c start " + a_pdf_file.toString());
        return;
      } catch (Exception e) {
        // skip and try next viewer
      }
    }
    // Try everything else in the list.
    for (int i = 0; i < DEFAULT_PDF_VIEWERS.length; i++) {
      try {
        arguments[0] = DEFAULT_PDF_VIEWERS[i];
        viewer_process = runtime.exec(arguments);
        return;
      } catch (Exception e) {
        // skip and try next viewer
      }
    }
  }

  /**
   * A test routine.  Will show the specified files, or if no files
   * are specified, the file <code>"./samples/KOA_rapport.pdf"</code>
   *
   * @param some_args a list of PDF files to preview.
   */
  public static void main(final String[] some_args) {
    try {
      if (some_args.length == 0) {
        showPdf(new File("./samples/KOA_rapport.pdf"));
        return;
      } else {
        for (int i = 0; i < some_args.length; i++) {
          showPdf(new File(some_args[i]));
        }
      }
    } catch (Exception e) {
      ; // ignore
    }
  }
}

