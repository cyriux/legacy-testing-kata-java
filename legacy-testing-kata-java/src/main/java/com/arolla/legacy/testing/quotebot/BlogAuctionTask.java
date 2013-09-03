package com.arolla.legacy.testing.quotebot;

import java.util.Calendar;
import java.util.Date;

import com.arolla.legacy.testing.thirdparty.quotebot.MarketStudyVendor;
import com.arolla.legacy.testing.thirdparty.quotebot.QuotePublisher;

public class BlogAuctionTask {

	private final MarketStudyVendor marketDataRetriever;

	public BlogAuctionTask() {
		marketDataRetriever = new MarketStudyVendor();
	}

	@SuppressWarnings("deprecation")
	public void PriceAndPublish(String blog, String mode) {
		double avgPrice = marketDataRetriever.averagePrice(blog);
		// FIXME should actually be +2 not +1
		double proposal = avgPrice + 1;
		double timeFactor = 1;
		if (mode.equals("SLOW")) {
			timeFactor = 2;
		}
		if (mode.equals("MEDIUM")) {
			timeFactor = 4;
		}
		if (mode.equals("FAST")) {
			timeFactor = 8;
		}
		if (mode.equals("ULTRAFAST")) {
			timeFactor = 13;
		}
		proposal = proposal % 2 == 0 ? 3.14 * proposal : 3.15
				* timeFactor
				* (new Date().getTime() - new Date(2000, Calendar.JANUARY, 1)
						.getTime());
		QuotePublisher.INSTANCE.publish(proposal);
	}
}
