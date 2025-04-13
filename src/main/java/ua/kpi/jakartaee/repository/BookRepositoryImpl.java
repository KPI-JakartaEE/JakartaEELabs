package ua.kpi.jakartaee.repository;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionManagement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.entity.Book;
import ua.kpi.jakartaee.entity.Author;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Probably do not need these two annotations: @TransactionManagement and @TransactionAttribute.
// Their behaviour is applied by default for EJB beans (@Stateless, @Singleton, @Stateful).
// If this annotation is not used, the bean is assumed to have container-managed transaction management.
@TransactionManagement
// If the TransactionAttribute annotation is not specified, and the bean uses container managed transaction demarcation, the semantics of the REQUIRED transaction attribute are assumed.
@TransactionAttribute

@Stateless
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        // NOTE(Yasnov): Initializing stub data.
        // TODO: Get rid of weird new Book(new BookDto) chain!
        save(new Book(new BookDto("Володар перснів", "Джон Р. Р. Толкін", "Епічне фентезі", Arrays.asList("казка", "пригоди", "фентезі"), "Величний твір Дж. P. P. Толкіна поєднує у собі героїчну романтику і наукову фантастику. Це захопливий пригодницький роман і, водночас, сповнена глибокої мудрості книга. Почергово то комічна й домашня, то епічна, а подекуди навіть страхітлива оповідь переходить через нескінченні зміни чудово описаних сцен і характерів. Основою цієї історії є боротьба за Перстень Влади, що випадково потрапив до рук гобіта Більбо Торбина. Саме цього Персня бракує Темному Володареві для того, щоби завоювати увесь світ. Тепер небезпечні пригоди випадають на долю Фродо Торбина, бо йому довірено цей Перстень. Він мусить залишити свій дім і вирушити у небезпечну мандрівку просторами Середзем’я аж до Судної Гори, що розташована в осерді володінь Темного Володаря. Саме там він має знищити Перстень і завадити втіленню лихого задуму.")));
        save(new Book(new BookDto("Гобіт, або Туди і звідти", "Джон Р. Р. Толкін", "Епічне фентезі", Arrays.asList("казка", "пригоди", "фентезі"), "Головний герой — гобіт Більбо Торбин, який живе спокійним життям у своїй затишній норі в Ширі. Але все змінюється, коли чарівник Ґандальф разом із компанією гномів на чолі з Торіном Дубощитом пропонує йому вирушити в небезпечну подорож. Метою цієї пригоди є далека гора, де гноми хочуть повернути своє втрачене королівство та скарби, захоплені могутнім драконом. На своєму шляху Більбо зустрічає різних істот: доброзичливих і ворожих, розумних і підступних. Йому доводиться проявити кмітливість і мужність, щоб подолати труднощі та відкрити в собі приховані здібності. Книга сповнена чарівних місць, несподіваних поворотів і чудового гумору. Вона досліджує теми дружби, відваги, сили духу та змін, які приходять із пригодами.")));
        save(new Book(new BookDto("1984", "Джордж Орвелл", "Антиутопія", Arrays.asList("антиутопія", "політика", "соціальне"), "Події розгортаються в уявному майбутньому. Поточний рік невідомий, але вважається, що це 1984. Велика частина світу перебуває у вічній війні. Велика Британія, тепер відома як Airstrip One, стала провінцією тоталітарної наддержави Океанія, яку очолює Великий Брат, диктаторський лідер, який підтримується інтенсивним культом особистості, створеним поліцією думки партії. Партія бере участь у всюдисущому урядовому нагляді та, через Міністерство правди, історичному запереченні та постійній пропаганді переслідування індивідуальності та незалежного мислення. Головний герой, Вінстон Сміт, старанний працівник середньої ланки в Міністерстві правди, який таємно ненавидить партію і мріє про повстання.")));
        save(new Book(new BookDto("Дюна", "Френк Герберт", "Наукова фантастика", Arrays.asList("роман", "фантастика", "пригоди"), "Дюна розгортається у далекому майбутньому у феодальному міжзоряному суспільстві, що походить від земних людей, у якому різні знатні будинки контролюють планетарні володіння. У ньому розповідається про молодого Пола Атрідеса, чия родина погоджується керувати планетою Арракіс. Хоча планета є негостинною та малонаселеною пустелею, вона є єдиним джерелом меланжу, або «спеції», ліків, які подовжують життя та покращують розумові здібності. Меланж також необхідний для космічної навігації, яка вимагає свого роду багатовимірного усвідомлення та передбачення, яке забезпечує лише препарат. Оскільки меланж можна виробляти лише на Арракісі, контроль над планетою є жаданою та небезпечною справою. Історія досліджує багатошарову взаємодію політики, релігії, екології, технологій і людських емоцій, коли фракції імперії протистоять одна одній у боротьбі за контроль над Арракісом і його спеціями.")));
        save(new Book(new BookDto("Лев, Біла Відьма та шафа", "Клайв Стейплз Льюїс", "Фентезі", Arrays.asList("роман", "фентезі", "дитяче"), "Це чарівна казка про чотирьох дітей, які відкривають шлях до магічного світу через звичайну шафу. Дія відбувається під час Другої світової війни, коли Пітер, Сьюзен, Едмунд і Люсі потрапляють до маєтку старого професора. Там молодша з них, Люсі, першою знаходить чарівну шафу, яка веде в Нарнію — країну, охоплену вічною зимою через правління злої Білої Відьми. Незабаром і решта дітей опиняється в цьому світі, де вони знайомляться з магічними істотами й дізнаються про великого лева Аслана, єдиного, хто може перемогти темні сили. У книзі поєднуються пригоди, магія, боротьба добра зі злом і важливі життєві уроки про чесність, відвагу та жертовність. Це класична історія, яка залишається популярною серед дітей і дорослих.")));
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByTitle} from {@link Book} to search for books by their title.
     */
    @Override
    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByTitle", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This is a case-sensitive implementation.
     * <p>
     * This method uses the named query {@code Book.findByPartOfTitle} from {@link Book}, which relies on a {@code LIKE} clause
     * to perform partial matching of the title.
     */
    @Override
    public List<Book> findByTitleSubstring(String title) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByTitleSubstring", Book.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findAll} from {@link Book} to fetch all records.
     */
    @Override
    public List<Book> findAll() {
        return em.createNamedQuery("Book.findAll", Book.class).getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByAuthorId} from {@link Book} to retrieve books associated with a particular author.
     */
    @Override
    public List<Book> findByAuthorId(UUID authorId) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorId", Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByAuthorName} from {@link Book},
     * which joins {@link Book} entity with the {@link Author} entity and filters by the author's name.
     */
    @Override
    public List<Book> findByAuthorName(String authorName) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorName", Book.class);
        query.setParameter("authorName", authorName);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfAuthorName} from {@link Book}, which performs a case-sensitive
     * partial match on the author's name.
     */
    @Override
    public List<Book> findByAuthorNameSubstring(String authorName) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByAuthorNameSubstring", Book.class);
        query.setParameter("authorName", "%" + authorName + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByGenre} from {@link Book}, which compares the genre field using equality.
     */
    @Override
    public List<Book> findByGenre(String genre) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByGenre", Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfGenre} from {@link Book}, which performs a case-sensitive
     * partial match using the {@code LIKE} operator.
     */
    @Override
    public List<Book> findByGenreSubstring(String genre) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByGenreSubstring", Book.class);
        query.setParameter("genre", "%" + genre + "%");
        return query.getResultList();
    }

    /**
     * <p>
     * This method uses the named query {@code Book.findByKeyword} from {@link Book}, which checks if the given keyword
     * exists in the {@code keywords} list of each book (case-sensitive).
     */
    @Override
    public List<Book> findByKeyword(String keyword) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByKeyword", Book.class);
        query.setParameter("keyword", keyword);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses the named query {@code Book.findByPartOfKeyword} from {@link Book}, which performs a case-sensitive
     * partial match on the keywords collection.
     */
    @Override
    public List<Book> findByKeywordSubstring(String keyword) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findByKeywordSubstring", Book.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#persist(Object)} to insert a new {@link Book} entity.
     */
    @Override
    public void save(Book book) {
        syncAuthor(book);
        em.persist(book);
    }


    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#merge(Object)} to update the given {@link Book} entity.
     * If the book does not already exist (i.e., the ID is not found in the database), it will be inserted as a new entity.
     */
    @Override
    public Optional<Book> update(Book book) {
        syncAuthor(book);
        return Optional.ofNullable(em.merge(book));
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses {@link EntityManager#remove(Object)} to remove the given {@link Book} entity from the database.
     * The entity must be managed by the {@link EntityManager} (i.e., it must be in the persistence context) for the removal to succeed.
     * If the entity is not found in the database, no action will be performed.
     */
    @Override
    public void delete(Book book) {
        em.remove(book);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method uses a named query to delete the {@link Book} with the given {@code id}.
     * If the {@code id} is not found, no rows will be affected in the database.
     */
    @Override
    public int deleteById(UUID id) {
        return em.createNamedQuery("Book.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Book> getBooksWithPagination(int pageNumber, int pageSize) {
        if (pageNumber < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }
        return em.createQuery("SELECT b FROM Book b", Book.class)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<Book> findBooksFilteredByFields(
        String title,
        String authorName,
        String genre,
        String keyword
    ) {
        TypedQuery<Book> query = em.createNamedQuery("Book.findBooksFilteredByAllFields", Book.class);
        query.setParameter("title", "%" + title + "%");
        query.setParameter("authorName", "%" + authorName + "%");
        query.setParameter("genre", "%" + genre + "%");
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public boolean existsById(UUID id) {
        Long count = em.createNamedQuery("Book.countById", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitle(String title) {
        Long count = em.createNamedQuery("Book.countByTitle", Long.class)
                .setParameter("title", title)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitleAndAuthorId(String title, UUID authorId) {
        Long count = em.createNamedQuery("Book.countByTitleAndAuthorId", Long.class)
                .setParameter("title", title)
                .setParameter("authorId", authorId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitleAndAuthorName(String title, String authorName) {
        Long count = em.createNamedQuery("Book.countByTitleAndAuthorName", Long.class)
                .setParameter("title", title)
                .setParameter("authorName", authorName)
                .getSingleResult();
        return count > 0;
    }


    // Do not understand why we need to sync author.
    // If book will be in transaction (a.k.a in managed state) then we can get author from DB just by calling book.getAuthor()
    // Default fetch type for @ManyToOne(cascade = CascadeType.ALL) is EAGER
    // JPA should access the DB automatically when it sees: book.getAuthor()
    // If it is not in managed state (a.k.a in transaction) then service should make another call
    // But it is most probably in transaction if we use EJB beans like @Stateless etc.
    /**
     * Synchronizes Book`s author with DB to be unique.
     * @param book for which to sync author.
     */
    void syncAuthor(Book book) {
        Author author = book.getAuthor();
        author = em.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", author.getName())
                .getResultStream()
                .findFirst()
                .orElse(author);
        book.setAuthor(author);
    }

}
