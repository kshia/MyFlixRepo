# Assignment 2 - *Flixster*

**Flixster** a read-only movie listing app using the Movie Database API

Submitted by: **Kristina Shia**

Time spent: **4** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

The following **optional** features are implemented:

* [X] Views should be responsive for both landscape/portrait mode and fully optimized for performance with the ViewHolder pattern.
* [X] User can pull-to-refresh the popular stream with SwipeRefreshLayout.
* [X] Displays a nice default placeholder graphic for each image during loading (read more about Picasso).
* [X] Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity. (3 points)
* [ ] Improve the user interface through styling and coloring (1 to 5 points depending on the difficulty of UI improvements).
* [X] Stretch: For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous ListViews and use different ViewHolder layout files for popular movies and less popular ones. (2 points)
* [X] Stretch: Add a rounded corners for the images using the Picasso transformations. (1 point)
* [ ] Stretch: Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
* [ ] Stretch: Allow video posts to be played in full-screen using the YouTubePlayerView (2 points)
  * When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played 		immediately.
  * Less popular videos rely on the detailed page should show an image preview that can initiate playing a 		YouTube video.
  * Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).


The following **additional** features are implemented:

* Use a custom launcher icon for the app
* Overview truncates with ellipsis so each element in the list is the same size, regardless of length of overview
* Display decimal rating value next to RatingBar scaled out of 5
* Round popularity to 2 decimal places
* Scrollable synopsis in DetailsActivity
* Created landscape view for DetailsActivity using nested layouts and weights
* Backdrop image displayed in list only if orientation is portrait and backdrop exists, otherwise shows poster image with title and overview

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I had trouble with combining the ViewHolder pattern and the Heterogenous ListViews. 

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