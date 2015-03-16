package primeService.util;

/*
 * Checks if a number is Prime
 */
public final class CheckPrime{
	/*
	 * Checks if a number is prime
	 * @param n Number to Check
	 * @return Whether it's a prime or not
	 */
	public static boolean isPrime(int n){
		Debug.printMessage(2);
		for(int i=2; i*2 < n; i++){
			if(0 == n%i){
				return false;
			}
		}
		return true;
	}
}
