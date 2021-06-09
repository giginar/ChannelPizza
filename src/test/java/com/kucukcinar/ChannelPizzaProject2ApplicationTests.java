package com.kucukcinar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;


class ChannelPizzaProject2ApplicationTests {
	
	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int numberOne = 10;
		int numberTwo = 30;
		
		//when
		int result = underTest.add(numberOne, numberTwo);
		
		//then
		int expected = 40;
		assertThat(result).isEqualTo(expected);
	}
	
	class Calculator{
		int add(int a, int b) {
			return a + b;
		}
	}

}
