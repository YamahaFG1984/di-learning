package c4;

import static org.easymock.EasyMock.*;
import org.testng.annotations.Test;

public class BookCatalogTest {

	@Test
	public final void freeFormBookSearch() {
		Library mock = createStrictMock(Library.class);

		String criteria = "dependency injection";

		expect(mock.findByAuthor(criteria))
			.andReturn(null);

		expect(mock.findByKeyword(criteria))
			.andReturn(null);

		Book di = new Book("dependency injection");
		expect(mock.findByTitle(criteria))
			.andReturn(di);


		replay(mock);

		new SimpleBookCatalog(mock)
 			.search(criteria);

		verify(mock);

 	}
}
