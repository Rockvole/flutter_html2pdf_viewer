package com.rockvole.pdfview;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.print.PrintDocumentAdapter;
import android.webkit.WebView;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;


/**
 * PdfviewPlugin
 */
public class PdfviewPlugin implements MethodChannel.MethodCallHandler {

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "pdfview");
    PdfviewPlugin instance = new PdfviewPlugin(registrar);
    channel.setMethodCallHandler(instance);
  }

  private final Registrar mRegistrar;

  private PdfviewPlugin(Registrar registrar) {
    this.mRegistrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, MethodChannel.Result result) {
    if (call.method.equals("pdfview")) {
      if (!(call.arguments instanceof String)) {
        result.error("ARGUMENT_ERROR", "String argument expected", null);
        return;
      }
      final String text = (String) call.arguments;
      showReport(text);
      result.success(null);

    } else {
      result.notImplemented();
    }
  }

  private void showReport(String reportString) {
    WebView webView = new WebView(mRegistrar.context());
    webView.loadDataWithBaseURL(null, reportString, "text/HTML", "UTF-8", null);
    PrintManager printManager = (PrintManager) mRegistrar.activity().getSystemService(Context.PRINT_SERVICE);

    PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

    String jobName = "Pdf View Print Test";

    PrintAttributes attrib = new PrintAttributes.Builder().
            setMediaSize(PrintAttributes.MediaSize.NA_LETTER.asLandscape()).
            setMinMargins(PrintAttributes.Margins.NO_MARGINS).
            build();
    printManager.print(jobName, printAdapter, attrib);
  }


}
/*


public class PdfViewPlugin implements MethodChannel.MethodCallHandler {

  private static final String CHANNEL = "plugins.rockvole.com/pdfview";

  public static void registerWith(Registrar registrar) {
    MethodChannel channel = new MethodChannel(registrar.messenger(), CHANNEL);
    PdfViewPlugin instance = new PdfViewPlugin(registrar);
    channel.setMethodCallHandler(instance);
  }

  private final Registrar mRegistrar;

  private PdfViewPlugin(Registrar registrar) {
    this.mRegistrar = registrar;
  }

  @Override
  public void onMethodCall(MethodCall call, MethodChannel.Result result) {
    if (call.method.equals("share")) {
      if (!(call.arguments instanceof String)) {
        result.error("ARGUMENT_ERROR", "String argument expected", null);
        return;
      }
      final String text = (String) call.arguments;
      showReport(text);
      result.success(null);
    } else {
      result.error("UNKNOWN_METHOD", "Unknown share method called", null);
    }
  }

  private void showReport(String reportString) {
    WebView webView = new WebView(mRegistrar.context());
    webView.loadDataWithBaseURL(null, reportString, "text/HTML", "UTF-8", null);
    PrintManager printManager = (PrintManager) mRegistrar.activity().getSystemService(Context.PRINT_SERVICE);

    PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

    String jobName = "Pdf View Print Test";

    PrintAttributes attrib = new PrintAttributes.Builder().
            setMediaSize(PrintAttributes.MediaSize.NA_LETTER.asLandscape()).
            setMinMargins(PrintAttributes.Margins.NO_MARGINS).
            build();
    printManager.print(jobName, printAdapter, attrib);
  }

}
 */