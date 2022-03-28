# CleanArchStudy
# android clean architecture study
## Clean Architecture, Dagger Hilt, MVVM, AAC, Retrofit2, rxJava2, DataBinding, Coroutine, Glide ...
## Naver Search Api, Github Repository Api
+ Clean Architecture 스터디하기
+ 영화, 깃허브 저장소를 검색해 RecyclerView에 보여주는 예제

+ ### Presentation
  + 의존관계 : data, domain

  + view : RecyclerView사용, Glide 사용, DataBinding 사용, viewModel의 List를 구독한수 변화가 있다면 view 업데이트

  + viewModel : 의존성 주입으로 viewModel에서 Naver Api, Github Api 호출가능, 결과를 view에 알림



+ ### data
  + 의존관계 : domain

  + di : ApiModule, GithubRepositoryDataModule, GithubRepositoryModule, LocalDataModule, RemoteDataModule, RepositoryModule

  + ApiInterface 정의

  + Repository 구현

+ ### domain
  + 의존관계 : X

  + di : UseCaseModule

  + Model 정의

  + UseCase 정의

  + Repository 정의


![device-2022-03-29-005937 mp4_20220329_010042](https://user-images.githubusercontent.com/23303189/160439589-1bcc9857-325e-477b-a7be-876eedc7ef49.gif)
