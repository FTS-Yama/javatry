/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.colorbox;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.color.BoxColor;
import org.docksidestage.bizfw.colorbox.space.BoxSpace;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.DevilBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.DevilBoxTextNotFoundException;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom.SecretBox;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, not using Stream API. <br>
 * Show answer by log() for question of javadoc. <br>
 * <pre>
 * addition:
 * o e.g. "string in color-boxes" means String-type content in space of color-box
 * o don't fix the YourPrivateRoom class and color-box classes
 * </pre>
 * @author jflute
 * @author your_name_here
 */
public class Step11ClassicStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * How many lengths does color name of first color-boxes have? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        if (!colorBoxList.isEmpty()) {
            ColorBox colorBox = colorBoxList.get(0);
            BoxColor boxColor = colorBox.getColor();
            String colorName = boxColor.getColorName();
            int answer = colorName.length();
            log(answer + " (" + colorName + ")"); // also show name for visual check
        } else {
            log("*not found");
        }
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int intMax = 0;
        String lengthMax = "";

        if (!colorBoxList.isEmpty()) {
            // ColorBox型の色の名前が入ったリストを作成
            for (final ColorBox colorBox : colorBoxList) {
                final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (final BoxSpace boxSpace : boxSpaceList) {
                    if (boxSpace.getContent() != null) {
                        final Object boxContent = boxSpace.getContent();
                        final String boxContentToString = boxContent.toString();

                        if (boxContentToString.length() > intMax) {
                            intMax = boxContentToString.length();
                            lengthMax = boxContentToString;
                        }
                    }
                }
            }
            log("文字数：" + intMax, lengthMax);
        } else {
            log("*not found");
        }
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int intMax = 0;
        int intMin = 10000;

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (final BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContent() != null) {
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    if (boxContentToString.length() > intMax) {
                        intMax = boxContentToString.length();
                    }
                    if (boxContentToString.length() < intMin) {
                        intMin = boxContentToString.length();
                    }
                }
            }
        }
        log(intMax - intMin);
    }

    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (without sort) <br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (ソートなしで))
     */
    public void test_length_findSecondMax() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int intMax = 0;
        int intSecondMax = 0;

        String secondMax = "";

        if (!colorBoxList.isEmpty()) {
            // ColorBox型の色の名前が入ったリストを作成
            for (final ColorBox colorBox : colorBoxList) {
                final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    final Object boxContent = boxSpace.getContent();
                    if (boxContent != null) {
                        final String boxContentToString = boxContent.toString();
                        if (boxContentToString.length() > intMax) {
                            intMax = boxContentToString.length();
                        }
                    }
                }
            }

            for (final ColorBox colorBox : colorBoxList) {
                final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
                for (BoxSpace boxSpace : boxSpaceList) {
                    final Object boxContent = boxSpace.getContent();
                    if (boxContent != null) {
                        final String boxContentToString = boxContent.toString();
                        if (boxContentToString.length() < intMax && boxContentToString.length() > intSecondMax) {
                            intSecondMax = boxContentToString.length();
                            secondMax = boxContentToString;
                        }
                    }
                }
            }
            log(secondMax);
        } else {
            log("*not found");
        }
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int sum = 0;

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            for (BoxSpace boxSpace : boxSpaceList) {
                if (boxSpace.getContent() != null) {
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();
                    sum += boxContentToString.length();
                }
            }
        }
        log(sum);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int intMax = 0;
        String lengthMax = "";

        if (!colorBoxList.isEmpty()) {
            // ColorBox型の色の名前が入ったリストを作成
            for (final ColorBox colorBox : colorBoxList) {
                final BoxColor boxColor = colorBox.getColor();
                final String colorName = boxColor.getColorName();

                if (colorName.length() > intMax) {
                    intMax = colorName.length();
                    lengthMax = colorName;
                }
            }
            log(lengthMax);
        } else {
            log("*not found");
        }
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     * 継承関係・構造を理解する
     */
    public void test_startsWith_findFirstWord() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 最初の文字列が"Water"だったらカラーボックスの色を表示
                    if (boxContentToString.startsWith("Water")) {
                        final BoxColor colorWater = colorBox.getColor();
                        final String colorName = colorWater.getColorName();
                        log(colorName);
                    }
                }
            }
        }
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 最初の文字列が"front"だったらカラーボックスの色を表示
                    if (boxContentToString.endsWith("front")) {
                        final BoxColor colorWater = colorBox.getColor();
                        final String colorName = colorWater.getColorName();
                        log(colorName);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 最後の文字列が"front"だったらfrontの開始位置を表示
                    if (boxContentToString.endsWith("front")) {
                        log(boxContentToString.indexOf("front") + 1);
                    }
                }
            }
        }
    }

    /**
     * What number character is starting with the late "ど" of string containing two or more "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {
        // 前から見つけた「ど」と後ろから見つけた「ど」の位置が違った場合、
        // 後ろから見つけた「ど」の位置を前から数えた位置として表示したい。

        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null && boxSpace.getContent() != "") {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 「ど」を検索
                    final int upDo = boxContentToString.indexOf("ど");
                    final int backDo = boxContentToString.lastIndexOf("ど");

                    // 前と後ろの「ど」の位置を比較(違ったら後ろの位置を表示)
                    if (upDo != backDo) {
                        log(backDo + 1);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 最後の文字列がfrontだったら最初の一文字を検索
                    if (boxContentToString.endsWith("front")) {
                        final String firstSub = boxContentToString.substring(0, 1);
                        log(firstSub);
                    }
                }
            }
        }
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 最初の文字列がWaterだったら最後の一文字を検索
                    if (boxContentToString.startsWith("Water")) {
                        int boxLength = boxContentToString.length();
                        final String lastSub = boxContentToString.substring(boxLength - 1, boxLength);
                        log(lastSub);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        int deleteOSum = 0;

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // 文字"o"が含まれている文字列から文字"o"を削除し文字"o"を削除した文字列を数える
                    if (boxContentToString.contains("o")) {
                        final String deleteO = boxContentToString.replace("o", "");
                        deleteOSum += deleteO.length();
                    }
                }
            }
        }
        log(deleteOSum);
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {

                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();
                    final String boxContentToString = boxContent.toString();

                    // java.io.Fileのファイルセパレーターの文字を置き換える
                    if (boxContentToString.contains("/")) {
                        final String separatorConvert = boxContentToString.replace("/", "\\");
                        log(separatorConvert);
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sum = 0;

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示(boxSpaceをlogで表示するとBoxSpaceのtoString()の書式で表示される。)
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {
                    final Object boxContent = boxSpace.getContent();

                    // DevilBoxと継承関係だったら
                    if (boxContent instanceof DevilBox) {
                        DevilBox devilBox = (DevilBox) boxContent;
                        // getTextを取得するための呼び出し
                        devilBox.wakeUp();
                        devilBox.allowMe();
                        devilBox.open();

                        try {
                            log(devilBox.getText());
                            sum += devilBox.getText().length();
                        } catch (DevilBoxTextNotFoundException e) {
                            // getTextしてnullだったときの処理
                            log(e);
                        }
                    }
                }
            }
        }
        log(sum);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {
                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();

                    if (boxContent instanceof Map) {
                        Map map = (Map) boxContent; // mapの構造,メソッドの理解,mapクラス
                        log("map:{");
                        for (Object obj : map.keySet()) {
                            log(obj + "=" + map.get(obj) + ";");

                        }
                        log("}");
                    }
                }
            }
        }
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final List<BoxSpace> boxSpaceList = colorBox.getSpaceList();
            // ColorBox内のgetSpaceListを1つずつ表示
            for (final BoxSpace boxSpace : boxSpaceList) {

                // NullPointerExceptionを避ける
                if (boxSpace.getContent() != null) {
                    // nullじゃないboxSpaceのcontentをString型にする
                    final Object boxContent = boxSpace.getContent();

                    if (boxContent instanceof Map) {
                        Map map = (Map) boxContent; // mapの構造,メソッドの理解,mapクラス
                        log("map:{");
                        for (Object obj : map.keySet()) {
                            if (map.get(obj) instanceof Map) {
                                Map map2 = (Map) map.get(obj);

                                log("    map:{");
                                for (Object obj2 : map2.keySet()) {
                                    log("    " + obj2 + "=" + map2.get(obj2));
                                }
                                log("    }");
                            }
                            log(obj + "=" + map.get(obj) + ";");
                        }
                        log("}");
                    }
                }
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_flat() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final BoxColor boxColor = colorBox.getColor();
            final String colorName = boxColor.getColorName();

            // white判定
            if (colorName == "white") {
                BoxSpace boxSpace = colorBox.getSpaceList().get(0);
                SecretBox secretBox = (SecretBox) boxSpace.getContent();
                String secStr = secretBox.getText();
                String secStrBlank = secStr.substring(secStr.indexOf(" ") + 1, secStr.lastIndexOf(" "));

                String asPrivate = secStrBlank;
                Map<String, String> map = new HashMap<String, String>();

                String[] split = asPrivate.split(";");
                for (String splitList : split) {
                    String[] split2 = splitList.split("=");
                    map.put(split2[0], split2[1]);
                }
                log(map.toString());
            }
        }
    }

    /**
     * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
     * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
     */
    public void test_parseMap_nested() {
        final List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();

        for (final ColorBox colorBox : colorBoxList) {
            final BoxColor boxColor = colorBox.getColor();
            final String colorName = boxColor.getColorName();

            // white判定
            if (colorName == "white") {
                BoxSpace middoleBox = colorBox.getSpaceList().get(1);
                MapToString(middoleBox);

                // lower
                BoxSpace lowerBox = colorBox.getSpaceList().get(2);
                MapToString(lowerBox);
            }
        }
    }

    // Stringをmapに変換しtoStringで出力するメソッド
    private void MapToString(final BoxSpace boxSpace) {
        SecretBox secretBox = (SecretBox) boxSpace.getContent();
        String secStr = secretBox.getText();

        // 最初と最後の空白を除去 (map:{}を含めないため)
        String secStrBlank = secStr.substring(secStr.indexOf(" ") + 1, secStr.lastIndexOf(" "));
        // log(secStrBlank);

        Map<Object, Object> map = new LinkedHashMap<Object, Object>();
        String[] split;

        for (int i = 0; i < secStrBlank.length();) {

            // セミコロンの場所を入れる
            int semicoronPoint = secStrBlank.indexOf(";", i);

            // セミコロンが見つからなかったら文字数を入れる
            if (semicoronPoint == -1) {
                semicoronPoint = secStrBlank.length();
            }

            // mapに入れる文字列を切り出す
            String separateStr = secStrBlank.substring(i, semicoronPoint);

            if (separateStr.contains("map")) {

                // "}"の場所を入れる
                int mapEnd = secStrBlank.indexOf("}");

                // mapの後ろの";"の場所を入れる
                int mapSemicoronPoint = secStrBlank.indexOf(";", mapEnd);

                // mapの後ろに";"が無かったら文字数を入れる
                if (mapSemicoronPoint == -1) {
                    mapSemicoronPoint = secStrBlank.length();
                }

                // mapの後ろの";"までを区切る
                String separateMap = secStrBlank.substring(i, mapSemicoronPoint);

                i = mapSemicoronPoint + 1;

                split = separateMap.split("=");
                map.put(split[0], BuildMap2(secStrBlank));

            } else {

                i = semicoronPoint + 1;

                split = separateStr.split("=");
                map.put(split[0], split[1]);

            }
        }
        log(map.toString());
    }

    private Map<String, String> BuildMap2(String secStrBlank) {
        Map<String, String> map = new LinkedHashMap<String, String>();

        // 内側のmapを切り出し
        String serchMap = secStrBlank.substring(secStrBlank.indexOf("map"), secStrBlank.indexOf("}") + 1);
        String serchMap2 = serchMap.substring(serchMap.indexOf(" ") + 1, serchMap.lastIndexOf(" "));

        String[] split = serchMap2.split(";");
        for (String splitList : split) {
            String[] split2 = splitList.split("=");
            map.put(split2[0], split2[1]);
        }

        return map;
    }
}
