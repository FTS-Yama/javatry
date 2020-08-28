package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BarkingProcess {

    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        downHitPoint();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        downHitPoint();
    }

    protected abstract String getBarkWord();

    // BarkingProcessが親なのでDownHitPointを抽象メソッドで定義
    protected abstract void downHitPoint();

    protected BarkedSound doBark(String barkWord) {
        downHitPoint();
        return new BarkedSound(barkWord);
    }

}
