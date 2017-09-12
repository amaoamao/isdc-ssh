package service;

import entity.ApplicationForm;

import java.util.Optional;

/**
 * Copyright (c) 2017 Peter Mao. All rights reserved.
 * Created by mao on 17-9-10.
 */
public interface JoinService {
    void submit(ApplicationForm applicationForm);

    Optional<ApplicationForm> queryForm(String openid);
}
