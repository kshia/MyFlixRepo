# Project 2 - *Flixster*

**Flixster** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **12** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **scroll through current movies** from the Movie Database API

The following **optional** features are implemented:

* [X] For each movie displayed, user can see the following details:
  * [X] Title, Poster Image, Overview (Portrait mode)
  * [X] Title, Backdrop Image, Overview (Landscape mode)
* [X] Layout is optimized with the [ViewHolder](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView#improving-performance-with-the-viewholder-pattern) pattern.
* [X] Display a nice default [placeholder graphic](http://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#configuring-picasso) for each image during loading.
* [X] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [X] Improved the user interface by experimenting with styling and coloring.
* [X] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [X] Allow video trailers to be played in full-screen using the YouTubePlayerView from the details screen.

The following **additional** features are implemented:

* User can pull-to-refresh the popular stream with SwipeRefreshLayout.
* When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video is played 		immediately.
* Less popular videos rely on the detailed page should show an image preview that can initiate playing a 		YouTube video.
* Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
* Use a custom launcher icon for the app
* Overview truncates with ellipsis so each element in the list is the same size, regardless of length of overview
* Display decimal rating value next to RatingBar scaled out of 5
* Round popularity to 2 decimal places
* Scrollable synopsis in DetailsActivity
* Created landscape view for DetailsActivity using nested layouts and weights
* Backdrop image displayed in list only if orientation is portrait and backdrop exists, otherwise shows poster image with title and overview
* Used purple play button in desperate attempt to make app less ugly

## Video Walkthrough

Here's a walkthrough of implemented user stories:


<img src='http://i.imgur.com/UDy0JQa.gif' title='Video Walkthrough 1' width='' alt='Video Walkthrough' />

<img src='http://i.imgur.com/Nys7bBb.gif' title='Video Walkthrough 2' width='' alt='Video Walkthrough' />

<img src='http://i.imgur.com/CzLg5pw.gif' title='Video Walkthrough 3' width='' alt='Video Walkthrough' />


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I had trouble with combining the ViewHolder pattern and the Heterogenous ListViews. Making the emulator work with Youtube was also difficult.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [2016] [Kristina Shia]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
