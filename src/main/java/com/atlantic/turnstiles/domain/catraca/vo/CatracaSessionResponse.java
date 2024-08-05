package com.atlantic.turnstiles.domain.catraca.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CatracaSessionResponse(@JsonAlias("session") String session) {

}
