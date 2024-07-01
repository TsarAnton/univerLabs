import java.util.List;

import domain.Author;
import ioc.ContainerException;
import ioc.IocContainer;
import service.AuthorService;
import service.ServiceException;

public class TestAuthorService {
	public static void main(String[] args) {
		try(IocContainer container = new IocContainer()) {
			Class.forName("org.postgresql.Driver");
			System.out.println("Loading driver OK");

			AuthorService service = container.getAuthorService();

			List<Author> authors = service.findAll();
			for(Author author : authors) {
				System.out.printf(
					"ID = %d, NAME = %s, SURNAME = %s\n",
					author.getId(),
					author.getName(),
					author.getSurname()
				);
			}

		} catch(ContainerException | ClassNotFoundException | ServiceException e) {
			System.out.println("Error. " + e.getMessage());
		}
	}
}
