package juc._4_synchronizer;

import java.util.concurrent.Phaser;

import juc.util.JucUtil;

public class Synchronizer_Phaser {
    private Phaser phaser = new Phaser(4) {
        protected boolean onAdvance(int phase, int registeredParties) {
            JucUtil.print("第" + (phase + 1) + "次到达, 总共 " + registeredParties + "人次");
            return super.onAdvance(phase, registeredParties);
        }
    };

    public static void main(String[] args) throws Exception {
        Synchronizer_Phaser synchronizerPhaser = new Synchronizer_Phaser();
        for (int i = 0; i < 4; i++) {
            new Thread(synchronizerPhaser.new MyRunnable("user_" + i)).start();
        }
    }

    class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        public void run() {
            switch (phaser.getPhase()) {
                case 0 :
                    if (!goTo("Start", phaser)) {
                        break;
                    }
                case 1 :
                    if (!goTo("Center", phaser)) {
                        break;
                    }
                case 2 :
                    if (!goTo("End", phaser)) {
                        break;
                    }
            }
        }

        private boolean goTo(String place, Phaser phaser) {
            int random = (int) (System.nanoTime() % 3);
            if (random == 0) {
                JucUtil.print(name + " leave");
                phaser.arriveAndDeregister();
                return false;
            } else if (random == 1) {
                phaser.bulkRegister(1);
                JucUtil.print(name + "_1 join");
                new Thread(new MyRunnable(name + "_1")).start();
            }

            JucUtil.print(name + " arrive " + place);
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
}
