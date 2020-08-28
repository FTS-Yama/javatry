package org.docksidestage.bizfw.basic.objanimal;

import org.docksidestage.bizfw.basic.objanimal.jump.HighJump;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rabbit extends Animal implements HighJump {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(Rabbit.class);

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Rabbit() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    protected final String getBarkWord() {
        return "pyon"; // bow? in English
    }

    // ===================================================================================
    //                                                                               Jump
    //                                                                              ======
    @Override
    public final void jump() {
        // dummy implementation
        logger.debug("...Jumping now");
    }

}
