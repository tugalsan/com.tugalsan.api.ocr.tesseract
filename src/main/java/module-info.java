module com.tugalsan.api.ocr.tesseract {
    requires java.desktop;
    requires tess4j;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.union;
    requires com.tugalsan.api.function;
    exports com.tugalsan.api.ocr.tesseract.server;
}
