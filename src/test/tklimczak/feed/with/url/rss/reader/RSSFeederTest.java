package tklimczak.feed.with.url.rss.reader;

import org.junit.Assert;
import org.junit.Test;
import tklimczak.feed.with.url.rss.model.Feed;
import tklimczak.feed.with.url.rss.model.FeedMessage;
import tklimczak.feed.with.url.rss.reader.FeedParser;

public class RSSFeederTest {
    @Test
    public void test() {
        FeedParser parser = new FeedParser();
        Feed feed = parser.readFeed("https://tvn24.pl/najnowsze.xml");
        Assert.assertNotNull(feed);
        Assert.assertNotNull(feed.getMessages());
        Assert.assertTrue(feed.getMessages().size() > 0);
    }
}
