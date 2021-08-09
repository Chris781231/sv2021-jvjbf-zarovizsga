package org.training360.finalexam.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {

    public EntityNotFoundException() {
        super(URI.create("teams/not-found"),
                "Not found",
                Status.NOT_FOUND,
                "Teams not found"
        );
    }
}
