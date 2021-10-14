# Rick&Morty-Archive
Publishing a copy of this application on the Play Store will only get your account banned.

### Used REST API: https://rickandmortyapi.com/
### [App Folder](Rick%26Morty-Archive)
###
### Architecture: 
Clean, MVP, Use-Cases, Mappers, Dependency Inversion.
### Libraries: 
Dagger2, RxJava3, Room, Retrofit, Moxy, Cicerone, Paging3, Glide.
###
https://user-images.githubusercontent.com/57148020/137371473-df5df167-5ad2-458f-9c14-10ab7304c6a9.mp4

![image](https://user-images.githubusercontent.com/57148020/137370545-3e2e17a2-0f14-4155-bd9e-4c4b99274556.png)|![image](https://user-images.githubusercontent.com/57148020/137370440-faf96f53-e616-49c4-ab5e-fd459f62be66.png)|![image](https://user-images.githubusercontent.com/57148020/137370582-907aa0c6-dd6b-41bf-be9a-585433fb2cac.png)|
|----------|---------|-----------|

### Выводы
 - RxJava и Paging 3 недостаточно хорошо дружат. Возможно, виноваты баги экспериментального RemoteMediator.
 - Use-case с RxJava выглядит хуже, чем с coroutines+flow
 - C GraphQL тут было бы проще. А так пришлось изворачиваться, чтобы не оверхедить лишними Dao и мапперами. Room @Relation не помогло добиться сокращения кода.