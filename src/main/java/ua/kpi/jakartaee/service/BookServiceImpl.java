package ua.kpi.jakartaee.service;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.exceptions.BookServiceException;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Singleton(name = "bookServiceImpl") // TODO It is better to use @Stateless instead of @Singleton when a proper DB implementation will be used
public class BookServiceImpl implements BookService {
    // TODO(Yasnov): Use DB!!! This is not persistent! At least this is thread-safe...
    private final List<BookDto> books = new CopyOnWriteArrayList<>();

    public BookServiceImpl() throws BookServiceException {
        // TODO(Yasnov): This is just a stub for demonstration, BookServiceImpl should use proper DB!
        addBook(new BookDto("Володар перснів", "Джон Р. Р. Толкін", "Епічне фентезі", Arrays.asList("казка", "пригоди", "фентезі"), "Величний твір Дж. P. P. Толкіна поєднує у собі героїчну романтику і наукову фантастику. Це захопливий пригодницький роман і, водночас, сповнена глибокої мудрості книга. Почергово то комічна й домашня, то епічна, а подекуди навіть страхітлива оповідь переходить через нескінченні зміни чудово описаних сцен і характерів. Основою цієї історії є боротьба за Перстень Влади, що випадково потрапив до рук гобіта Більбо Торбина. Саме цього Персня бракує Темному Володареві для того, щоби завоювати увесь світ. Тепер небезпечні пригоди випадають на долю Фродо Торбина, бо йому довірено цей Перстень. Він мусить залишити свій дім і вирушити у небезпечну мандрівку просторами Середзем’я аж до Судної Гори, що розташована в осерді володінь Темного Володаря. Саме там він має знищити Перстень і завадити втіленню лихого задуму."));
        addBook(new BookDto("Гобіт, або Туди і звідти", "Джон Р. Р. Толкін", "Епічне фентезі", Arrays.asList("казка", "пригоди", "фентезі"), "Головний герой — гобіт Більбо Торбин, який живе спокійним життям у своїй затишній норі в Ширі. Але все змінюється, коли чарівник Ґандальф разом із компанією гномів на чолі з Торіном Дубощитом пропонує йому вирушити в небезпечну подорож. Метою цієї пригоди є далека гора, де гноми хочуть повернути своє втрачене королівство та скарби, захоплені могутнім драконом. На своєму шляху Більбо зустрічає різних істот: доброзичливих і ворожих, розумних і підступних. Йому доводиться проявити кмітливість і мужність, щоб подолати труднощі та відкрити в собі приховані здібності. Книга сповнена чарівних місць, несподіваних поворотів і чудового гумору. Вона досліджує теми дружби, відваги, сили духу та змін, які приходять із пригодами."));
        addBook(new BookDto("1984", "Джордж Орвелл", "Антиутопія", Arrays.asList("антиутопія", "політика", "соціальне"), "Події розгортаються в уявному майбутньому. Поточний рік невідомий, але вважається, що це 1984. Велика частина світу перебуває у вічній війні. Велика Британія, тепер відома як Airstrip One, стала провінцією тоталітарної наддержави Океанія, яку очолює Великий Брат, диктаторський лідер, який підтримується інтенсивним культом особистості, створеним поліцією думки партії. Партія бере участь у всюдисущому урядовому нагляді та, через Міністерство правди, історичному запереченні та постійній пропаганді переслідування індивідуальності та незалежного мислення. Головний герой, Вінстон Сміт, старанний працівник середньої ланки в Міністерстві правди, який таємно ненавидить партію і мріє про повстання."));
        addBook(new BookDto("Дюна", "Френк Герберт", "Наукова фантастика", Arrays.asList("роман", "фантастика", "пригоди"), "Дюна розгортається у далекому майбутньому у феодальному міжзоряному суспільстві, що походить від земних людей, у якому різні знатні будинки контролюють планетарні володіння. У ньому розповідається про молодого Пола Атрідеса, чия родина погоджується керувати планетою Арракіс. Хоча планета є негостинною та малонаселеною пустелею, вона є єдиним джерелом меланжу, або «спеції», ліків, які подовжують життя та покращують розумові здібності. Меланж також необхідний для космічної навігації, яка вимагає свого роду багатовимірного усвідомлення та передбачення, яке забезпечує лише препарат. Оскільки меланж можна виробляти лише на Арракісі, контроль над планетою є жаданою та небезпечною справою. Історія досліджує багатошарову взаємодію політики, релігії, екології, технологій і людських емоцій, коли фракції імперії протистоять одна одній у боротьбі за контроль над Арракісом і його спеціями."));
        addBook(new BookDto("Лев, Біла Відьма та шафа", "Клайв Стейплз Льюїс", "Фентезі", Arrays.asList("роман", "фентезі", "дитяче"), "Це чарівна казка про чотирьох дітей, які відкривають шлях до магічного світу через звичайну шафу. Дія відбувається під час Другої світової війни, коли Пітер, Сьюзен, Едмунд і Люсі потрапляють до маєтку старого професора. Там молодша з них, Люсі, першою знаходить чарівну шафу, яка веде в Нарнію — країну, охоплену вічною зимою через правління злої Білої Відьми. Незабаром і решта дітей опиняється в цьому світі, де вони знайомляться з магічними істотами й дізнаються про великого лева Аслана, єдиного, хто може перемогти темні сили. У книзі поєднуються пригоди, магія, боротьба добра зі злом і важливі життєві уроки про чесність, відвагу та жертовність. Це класична історія, яка залишається популярною серед дітей і дорослих."));
    }

    /**
     * @param bookId - book unique ID
     * @return index, -1 if not found
     */
    private int findBookIndex(String bookId) {
        // Books will have unique IDs for sure, so only findFirst.
        return IntStream.range(0, books.size())
                .filter(i -> Objects.equals(books.get(i).getBookId(), bookId))
                .findFirst().orElse(-1);
    }

    private List<String> filterKeywords(List<String> keywords) {
        return keywords.stream()
                .filter(keyword -> keyword != null && !keyword.isBlank())
                .collect(Collectors.toList());
    }

    @Override
    public void addBook(BookDto bookDto) throws BookServiceException {
        // Chance of collision should be zero to none...
        bookDto.setBookId(UUID.randomUUID().toString());
        bookDto.setKeywords(filterKeywords(bookDto.getKeywords()));
        books.add(0, bookDto);
    }

    @Override
    @Lock(LockType.READ) // TODO(Hliuza) This Lock won't be needed when DB will be used
    public List<BookDto> getBooks() {
        return books;
    }

    @Override
    @Lock(LockType.READ)  // TODO(Hliuza) This Lock won't be needed when DB will be used
    public List<BookDto> getBooks(String author, String title, String keyword, String genre) {
        // TODO(Yasnov): This is garbage, we should not copy storage each time!
        List<BookDto> filteredBooks = new ArrayList<>(books);

        if (author != null && !author.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList());
        }
        if (title != null && !title.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
        }
        if (keyword != null && !keyword.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(book -> book.getKeywords().stream().anyMatch(k -> k.toLowerCase().contains(keyword.toLowerCase()))).collect(Collectors.toList());
        }
        if (genre != null && !genre.isEmpty()) {
            filteredBooks = filteredBooks.stream().filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase())).collect(Collectors.toList());
        }

        return filteredBooks;
    }

    @Override
    public void updateBook(BookDto bookDto) throws BookServiceException {
        final String bookId = bookDto.getBookId();
        final int bookIndex = findBookIndex(bookId);

        if (bookIndex == -1) {
            throw new BookServiceException("No book found with id " + bookId + ". Update failed.");
        }

        bookDto.setKeywords(filterKeywords(bookDto.getKeywords()));
        books.set(bookIndex, bookDto);
    }

    @Override
    public void deleteBookById(String bookId) throws BookServiceException {
        final boolean removed = books.removeIf(bookDTO -> bookDTO.getBookId().equals(bookId));

        if (!removed) {
            throw new BookServiceException("No book found with id " + bookId + ". Deletion failed.");
        }
    }
}
