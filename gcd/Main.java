package gcd;

import java.util.ArrayList;
import java.io.IOException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Main {

	public static void main(String[] args) throws FailingHttpStatusCodeException, IOException {

		WebClient client = new WebClient();

		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);

		HtmlPage page = client.getPage("http://quantbet.com/quiz/dev");
		HtmlInput input = page.getElementByName("divisor");

		int num1 = Integer.parseInt(page.getElementsByTagName("strong").get(0).getTextContent());
		int num2 = Integer.parseInt(page.getElementsByTagName("strong").get(1).getTextContent());

		input.type(Integer.toString(greatestCommonDivisor(num1, num2)));

		HtmlElement button = (HtmlElement) page.getByXPath("//button").get(0);

		page = button.click();

		System.out.println(page.asText());

		client.close();
	}

	public static int greatestCommonDivisor(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return greatestCommonDivisor(num2, num1 % num2);
	}

}
