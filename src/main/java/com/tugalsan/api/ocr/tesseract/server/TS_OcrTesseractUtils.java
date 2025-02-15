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
        return "tur";
    }

    public String lng_eng() {
        return "eng";
    }

    public static enum OCR_ENGINE_MODE {
        Legacy(0), LSTM(1), LegacyAndLSTM(2), WhataeverAvailable_Default(3);

        OCR_ENGINE_MODE(int newValue) {
            value = newValue;
        }
        private final int value;

        public int getValue() {
            return value;
        }
    }

    public static enum PAGE_SEG_MODE {
        OSD(0), UseAutomaticPageSegWithOSD(1), UseAutomaticPageSeg_NotImplemented(2),
        UseAutomaticPageSeg_Default(3), AssumeSingleColumnText(4),
        AssumeSingleuniformBlockOfVerticallyAlligned(5),
        AssumeSingleuniformBlock(6), AssumeSingleLine(7),
        AssumeSingleWord(8), AssumeSingleWordInACircle(9),
        AssumeSingleCharacter(10), TryToFindAllText(11),
        TryToFindAllTextWithOSD(12), AssumeSingleLineNoHacks(13);

        PAGE_SEG_MODE(int newValue) {
            value = newValue;
        }
        private final int value;

        public int getValue() {
            return value;
        }
    }

    public static TGS_UnionExcuse<String> ocr(BufferedImage bi, Path dataDir, PAGE_SEG_MODE pageSegMode, OCR_ENGINE_MODE ocrEngineMode, String... lng) {
        return TGS_FuncMTCEUtils.call(() -> {
            var t = new Tesseract();
            t.setDatapath(dataDir.toString());
            if (lng != null && lng.length != 0) {
                t.setLanguage(Arrays.stream(lng).collect(Collectors.joining("+")));
            }
            t.setPageSegMode(pageSegMode.value);
            t.setOcrEngineMode(ocrEngineMode.value);
            return TGS_UnionExcuse.of(t.doOCR(bi));
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
