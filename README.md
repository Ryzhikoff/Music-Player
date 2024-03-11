# Решение тестового задания
## Задание:

- реализовать главный экран:
   - Отображает список плейлистов, доступных для прослушивания. Сразу после запуска приложения на главном экране выполняется GET-запрос
   - Отображать плейлисты с названием и картинками в два столбца
- реализовать экран просмотра плейлиста:
   - Получение треков плейлиста выполняется с помощью GET-запроса
   - Отображать список треков плейлиста в одну колонку
   - В элементах выводить название трека (поле "name"), автора трека (поле "author"), картинку трека (поле "image")
   - Кнопку "Play". При нажатии кнопки "Play" должно начинаться воспроизведение трека по url (поле "url"), кнопка "Play" должна меняться на кнопку "Pause"
   - Нажатие на кнопку "Pause" должно ставить воспроизведение на паузу.
- Внизу экрана отображать плашку воспроизведения текущего трека, в которой дублируется текущий воспроизводимый трек. При возврате на главный экран плашка плеера должна оставаться внизу экрана и текущий воспроизводимый трек должен продолжать проигрывание.

    ---
<p align="left">
<img src="https://github.com/Ryzhikoff/Music-Player/blob/master/screens/1.png" height="300">
<img src="https://github.com/Ryzhikoff/Music-Player/blob/master/screens/2.png" height="300">
<img src="https://github.com/Ryzhikoff/Music-Player/blob/master/screens/3.png" height="300">
<img src="https://github.com/Ryzhikoff/Music-Player/blob/master/screens/4.png" height="300">

</p>

[Со основу взят дизайн отсюда](https://www.figma.com/file/94v60YAx8NQin4jb2M627v/Music-Playlist-Design-(Community)?type=design&node-id=14-2&mode=design&t=dgun8zbIrwrDaG86-0)

  ## Стек:
  - Kotlin
  - Coroutines
  - Flow
  - ﻿﻿Dagger
  - Paging 3
  - Glide
  - Retrofit
  - Navigation Component
  - ﻿﻿MVVM
  - XML
  - Clean Architecture
  - Многомодульность

## Схема взаимодействия компонентов в приложении:

 <p align="left">
  <img src="https://github.com/Ryzhikoff/Music-Player/blob/master/screens/mediaplayer.png" height="300">
</p>

