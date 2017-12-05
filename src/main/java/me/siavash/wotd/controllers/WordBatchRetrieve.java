package me.siavash.wotd.controllers;

import me.siavash.wotd.Response;
import me.siavash.wotd.entities.FlatWord;
import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

import static me.siavash.wotd.util.Utils.parseDate;
import static me.siavash.wotd.util.Utils.validate;

@Controller
public class WordBatchRetrieve {

    private final WordRepository repository;

    @Autowired
    public WordBatchRetrieve(WordRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(method= RequestMethod.GET, path = "/batch/words")
    public Response<Map<String, Word>>
    getWords(@RequestParam(value = "dateBegin", required = false, defaultValue="today") String dateBegin,
                                  @RequestParam(value = "dateEnd", required = false, defaultValue="today") String dateEnd){
        if (validate(dateBegin, dateEnd)){
            String begin = LocalDate.parse(parseDate(dateBegin)).toString();
            String end = LocalDate.parse(parseDate(dateEnd)).toString();
            return new Response<>(repository.findWordsByDateRange(begin, end), HttpStatus.OK);
        }
        return new Response<>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(method= RequestMethod.GET, path = "/batch/flatwords")
    public Response<Map<String, FlatWord>>
    getFlatWords(@RequestParam(value = "dateBegin", required = false, defaultValue="today") String dateBegin,
                 @RequestParam(value = "dateEnd", required = false, defaultValue="today") String dateEnd){

        if (validate(dateBegin, dateEnd)){
            String begin = LocalDate.parse(parseDate(dateBegin)).toString();
            String end = LocalDate.parse(parseDate(dateEnd)).toString();
            return new Response<>(repository.findFlatWordsByDateRange(begin, end), HttpStatus.OK);
        }
        return new Response<>(HttpStatus.BAD_REQUEST);


    }

}
