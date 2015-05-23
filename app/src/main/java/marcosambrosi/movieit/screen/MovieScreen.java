/*
 *
 *
 *  *
 *  *  * Copyright (C)  2015 Marcos Ambrosi marcosambrosi@gmail.com
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package marcosambrosi.movieit.screen;

import flow.HasParent;
import flow.Layout;
import marcosambrosi.movieit.R;

/**
 * Created by Marcos on 14/05/2015.
 */
@Layout(R.layout.movie_view)
public class MovieScreen implements HasParent<ListMoviesScreen> {

    public long id;

    public MovieScreen(long id){
        this.id = id;
    }

    @Override
    public ListMoviesScreen getParent() {
        return new ListMoviesScreen();
    }
}
