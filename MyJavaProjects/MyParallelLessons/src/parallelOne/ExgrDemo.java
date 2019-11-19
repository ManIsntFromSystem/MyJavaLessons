package parallelOne;

import java.util.concurrent.Exchanger;

public class ExgrDemo {
    public static void main(String[] args) {
        Exchanger<String> exgr = new Exchanger<String>();
        System.out.println("1");
        new MakeString(exgr).run();
        System.out.println("2");
        new UseString(exgr).run();
    }
}

    class MakeString implements Runnable{
        Exchanger<String> ex;
        String str;
        public MakeString(Exchanger<String> c) {
            ex = c;
            str = new String();
            System.out.println("Make");
        }

        @Override
        public void run() {
            char ch = 'A';

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    str += (char) ch++;
                    System.out.println("for m");

                    try {
                        System.out.println("ex m");
                        str = ex.exchange(str);
                        System.out.println("Changed");
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }

    class UseString implements Runnable{
        Exchanger<String> ex;
        String str;

        public UseString(Exchanger<String> c) {
            ex = c;
        }

        @Override
        public void run() {

            for (int i = 0; i < 3; i++) {
                try {
                    str = ex.exchange(new String());
                    System.out.println("Received: " + str);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }

