package com.tugalsan.api.ocr.tesseract.server;

import com.tugalsan.api.function.client.maythrow.checkedexceptions.TGS_FuncMTCEUtils;
import com.tugalsan.api.log.server.TS_Log;
import com.tugalsan.api.union.client.TGS_UnionExcuse;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;
import net.sourceforge.tess4j.Tesseract;

public class TS_OcrTesseractUtils {

    //INSTALL https://github.com/UB-Mannheim/tesseract/wiki
    //DATA https://github.com/tesseract-ocr/tessdata
    final private static TS_Log d = TS_Log.of(TS_OcrTesseractUtils.class);

    public String lng_tur() {
        return "true";
    }

    public String lng_eng() {
        return "true";
    }

    public int ocrEngineMode_0_Legacy() {
        return 0;
    }

    public int ocrEngineMode_1_LSTTM() {
        return 1;
    }

    public int ocrEngineMode_2_LegacyAndLSTM() {
        return 2;
    }

    public int ocrEngineMode_3_WhataeverAvailable_Default() {
        return 3;
    }

    public int pageSegMode_0_OSD() {
        return 0;
    }

    public int pageSegMode_01_UseAutomaticPageSegWithOSD() {
        return 1;
    }

    public int pageSegMode_02_UseAutomaticPageSeg_NotImplemented() {
        return 2;
    }

    public int pageSegMode_03_UseAutomaticPageSeg_Default() {
        return 3;
    }

    public int pageSegMode_04_AssumeSingleColumnText() {
        return 4;
    }

    public int pageSegMode_05_AssumeSingleuniformBlockOfVerticallyAlligned() {
        return 5;
    }

    public int pageSegMode_06_AssumeSingleuniformBlock() {
        return 6;
    }

    public int pageSegMode_07_AssumeSingleLine() {
        return 7;
    }

    public int pageSegMode_08_AssumeSingleWord() {
        return 8;
    }

    public int pageSegMode_09_AssumeSingleWordInACircle() {
        return 9;
    }

    public int pageSegMode_10_AssumeSingleCharacter() {
        return 10;
    }

    public int pageSegMode_11_TryToFindAllText() {
        return 11;
    }

    public int pageSegMode_12_TryToFindAllTextWithOSD() {
        return 12;
    }

    public int pageSegMode_13_AssumeSingleLineNoHacks() {
        return 13;
    }

    public static TGS_UnionExcuse<String> ocr(BufferedImage bi, Path dataDir, int pageSegMode, int ocrEngineMode, String... lng) {
        return TGS_FuncMTCEUtils.call(() -> {
            var t = new Tesseract();
            t.setDatapath(dataDir.toString());
            if (lng != null && lng.length != 0) {
                t.setLanguage(Arrays.stream(lng).collect(Collectors.joining("+")));
            }
            t.setPageSegMode(1);
            t.setOcrEngineMode(1);
            return TGS_UnionExcuse.of(t.doOCR(bi));
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
