public class Kompliceret {

    public static void main(String[] args) {

        MyTimer.start();

        int n = 4; // Ikke højere end 5 da den tog 11 min.;

        long resultat = 0;

        for (int i = 0; i < n; i++) {
            System.out.print(".");
            for (long j = 0; j < i * n; j++) {
                for (long k = 0; k < j * Math.pow(j, 2); k++) {
                    for (long l = 0; l < Math.pow(k, 2); l++) {
                        resultat = i*j*k*l;
                    }

                }
            }
        }

        System.out.println();
        System.out.println("Resultat " + resultat);
        MyTimer.stop();

    }
}
