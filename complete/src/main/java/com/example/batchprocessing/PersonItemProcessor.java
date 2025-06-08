package com.example.batchprocessing;

import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	// Requires processor annotation of @StepScope
	private StepExecution stepExecution;

	// We do not initialize a value so that we can create an NPE later
	private String something;

    @Autowired
    private PersonService personService;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public Person process(final Person person) throws SkippableException {

        try {

            final String firstName = person.firstName().toUpperCase();
            final String lastName = person.lastName().toUpperCase();

            final Person transformedPerson = new Person(firstName, lastName);
            log.info("Converting ({}) into ({})", person, transformedPerson);

            // force NPE on last .csv record
            if (firstName.equals("JOHN")) {
                something.toUpperCase();
            }

            // Call service (which throws exception on records after john buck: requires chunk size 1 for committed data)
            @SuppressWarnings("unused")
            final Person result = personService.findByLastName("BUCK");

            return transformedPerson;
        } catch (Exception e) {
            log.info("Caught an EXCEPTION {}; making skippable.", e.getClass());

            // TODO Need to write to step execution context to probably create failed job
            ((CopyOnWriteArrayList<String>) stepExecution.getExecutionContext().get("stepReasons")).add("some reason for failure");

            throw new SkippableException(e);
        }

    }

}
