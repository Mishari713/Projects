package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {

	@Test
	void updatePlainTest() {//tests plain updater 
		Town weewee = new Town(4,4);
		weewee.randomInit(10);
		weewee = ISPBusiness.updatePlain(weewee);
		 assertEquals(weewee.toString(),"E E E E \nC C O E \nC O E O \nC E E E \n");//compares the updated plain with the expected result
	}
	
	@Test
	void getProfitTest() {//tests getprofit funtion
		Town weewee = new Town(4,4);
		weewee.randomInit(10);
		weewee = ISPBusiness.updatePlain(weewee);
		 assertEquals(ISPBusiness.getProfit(weewee),4);//compares it with the expected result
	}


}
