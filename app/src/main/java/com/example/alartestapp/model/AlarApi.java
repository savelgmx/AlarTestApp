package com.example.alartestapp.model;


import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlarApi {




/*
При нажатии идет к серверу
GET http://www.alarstudios.com/test/auth.cgi(параметры запроса: username=XXX, password=XXX),
он возвращает JSON.
Если "status" == "ok", то пропускаем, нет - показываем красиво,
что логин/пароль неправильные. Сервер выдаст "ok" на "test"/"123"
и тогда идем на следующий экран, запоминая "code". */
@GET("auth.cgi")
Single<AuthResponse> getAuthResponce(@Query(username) String username,
                                     @Query(password) String password);


 /*
 2. Таблица с данными.
 Данные получаем по GET http://www.alarstudios.com/test/data.cgi (параметры запроса: code=XXX из предыдущего шага, p=N - страница с 1), выдает по 10 элементов.
 
 В приложении - отображается как бесконечная пагинация. Доходим д
        о "низа" списка - подгружаем данные. Каждый элемент таблицы должен содержать картинку
         (выберите любой внешний URL)
 */
@GET("data.cgi")
 Single<DataResponse> getDataResponse(
 
);
 
}
