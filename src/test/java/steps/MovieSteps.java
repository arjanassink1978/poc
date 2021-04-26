package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import pojos.movie.PostMovie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieSteps extends AbstractSteps {
    @When("I create and post a movie payload with")
    public void iCreateAndPostAMoviePayloadWith(DataTable dataTable) {

        List<Map<String, String>> values = dataTable.asMaps(String.class, String.class);
        List<PostMovie> movies = values.stream().map(i -> objectMapper.convertValue(i, PostMovie.class)).collect(Collectors.toList());
        for (PostMovie movie : movies) {
            setResponse(postRequest(movie, "/movies"));
        }
    }
}
