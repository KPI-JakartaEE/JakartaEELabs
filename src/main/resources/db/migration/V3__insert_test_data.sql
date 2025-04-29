-- Insert test authors
INSERT INTO authors (name, biography) VALUES
                                          ('Джон Р. Р. Толкін', 'Автор епічних фентезі-романів, відомий своєю серією "Володар перснів" та "Хобіт".'),
                                          ('Джордж Орвелл', 'Британський письменник, найвідоміший за своїми антиутопіями "1984" і "Скотний двір".'),
                                          ('Френк Герберт', 'Американський письменник, відомий серією науково-фантастичних романів "Дюна".'),
                                          ('Клайв Стейплз Льюїс', 'Британський письменник, автор серії "Хроніки Нарнії" та інших фентезі-романів.');

-- Insert test books
INSERT INTO books (title, author_id, genre, description)
VALUES
    ('Володар перснів',
     (SELECT id FROM authors WHERE name = 'Джон Р. Р. Толкін'),
     'Епічне фентезі',
     'Величний твір Дж. P. P. Толкіна поєднує у собі героїчну романтику і наукову фантастику. Це захопливий пригодницький роман і, водночас, сповнена глибокої мудрості книга.'),

    ('Гобіт, або Туди і звідти',
     (SELECT id FROM authors WHERE name = 'Джон Р. Р. Толкін'),
     'Епічне фентезі',
     'Гобіт Більбо Торбин вирушає у пригодницьку подорож разом із гномами, щоб повернути втрачене королівство.'),

    ('1984',
     (SELECT id FROM authors WHERE name = 'Джордж Орвелл'),
     'Антиутопія',
     'Події відбуваються в тоталітарному світі, де контроль над думками є основною рисою держави.'),

    ('Дюна',
     (SELECT id FROM authors WHERE name = 'Френк Герберт'),
     'Наукова фантастика',
     'Роман, що розповідає про боротьбу за контроль над планетою Арракіс та її єдиним цінним ресурсом — спецій.'),

    ('Лев, Біла Відьма та шафа',
     (SELECT id FROM authors WHERE name = 'Клайв Стейплз Льюїс'),
     'Фентезі',
     'Чотири діти відкривають шлях до магічного світу через шафу, де вони зустрічають величного лева Аслана.');



-- Insert test keywords
INSERT INTO book_keywords (book_id, keyword)
VALUES
    ((SELECT id FROM books WHERE title = 'Володар перснів'), 'казка'),
    ((SELECT id FROM books WHERE title = 'Володар перснів'), 'пригоди'),
    ((SELECT id FROM books WHERE title = 'Володар перснів'), 'фентезі'),

    ((SELECT id FROM books WHERE title = 'Гобіт, або Туди і звідти'), 'казка'),
    ((SELECT id FROM books WHERE title = 'Гобіт, або Туди і звідти'), 'пригоди'),
    ((SELECT id FROM books WHERE title = 'Гобіт, або Туди і звідти'), 'фентезі'),

    ((SELECT id FROM books WHERE title = '1984'), 'антиутопія'),
    ((SELECT id FROM books WHERE title = '1984'), 'політика'),
    ((SELECT id FROM books WHERE title = '1984'), 'соціальне'),

    ((SELECT id FROM books WHERE title = 'Дюна'), 'роман'),
    ((SELECT id FROM books WHERE title = 'Дюна'), 'фантастика'),
    ((SELECT id FROM books WHERE title = 'Дюна'), 'пригоди'),

    ((SELECT id FROM books WHERE title = 'Лев, Біла Відьма та шафа'), 'роман'),
    ((SELECT id FROM books WHERE title = 'Лев, Біла Відьма та шафа'), 'фентезі'),
    ((SELECT id FROM books WHERE title = 'Лев, Біла Відьма та шафа'), 'дитяче');

