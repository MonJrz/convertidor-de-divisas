import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        //Desplegar el menú
        while (continuar) {
            System.out.println("");
            System.out.println("Bienvenido al convertidor de monedas");
            System.out.println("Opciones:");
            System.out.println("1. Convertir monedas");
            System.out.println("2. Ver códigos de monedas");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa la moneda base (ejemplo: USD): ");
                    String baseCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Ingresa la moneda objetivo (ejemplo: EUR): ");
                    String targetCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Ingresa la cantidad a convertir: ");
                    double amount = scanner.nextDouble();

                    try {
                        ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI();
                        double exchangeRate = exchangeRateAPI.getExchangeRate(baseCurrency, targetCurrency);

                        if (exchangeRate > 0) {
                            double convertedAmount = amount * exchangeRate;
                            System.out.printf("%f %s son equivalentes a %f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
                        } else {
                            System.out.println("No se pudo obtener la tasa de cambio.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    CodigosDeMonedas.mostrarCodigos();
                    break;

                case 3:
                    continuar = false;
                    System.out.println("Gracias por usar el convertidor de monedas. ¡Adiós!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}
