  В обох випадках, Work Stealing та Work Dealing, програма успішно виконує множення матриць і повертає очікувані результати. Часи виконання, однак, відрізняються, що вказує на важливість вибору стратегії розподілу задач в залежності від конкретної задачі та обчислювального навантаження.

Порівняння підходів:

Work Stealing. 
	В цьому підході потоки динамічно "крадуть" завдання з загальної черги завдань, які ще не були взяті до виконання. Це може бути ефективно в умовах нерівномірного навантаження, коли деякі потоки завершують свої завдання швидше за інших.
Часи виконання: З результатів видно, що часи виконання для Work Stealing можуть бути довшими, особливо при великій кількості рядків та стовпців у матрицях.

Work Dealing. 
  В цьому підході завдання розділяються і передаються потокам на початку виконання, так що кожен потік знає свій набір задач заздалегідь. Це може зменшити накладні витрати, пов'язані з управлінням чергами завдань та динамічним балансуванням.
Часи виконання: Згідно з результатами, Work Dealing показує значно кращі часи виконання у порівнянні з Work Stealing, особливо при великих розмірах матриць.

Висновки та рекомендації

  На основі аналізу можна зробити висновок, що підхід Work Dealing є ефективнішим для задачі множення матриць в умовах цієї програми. Швидше за все, це пов'язано з тим, що при множенні матриць, яке є досить інтенсивною по обчисленнях операцією, менша кількість накладних витрат на управління чергами та динамічне балансування може дати перевагу.
Рекомендується вибирати підхід Work Dealing для аналогічних завдань, особливо коли можна заздалегідь розподілити навантаження між потоками. Водночас, Work Stealing може виявитися корисним у більш непередбачуваних умовах виконання, де деякі завдання можуть вимагати непропорційно більше часу на виконання.