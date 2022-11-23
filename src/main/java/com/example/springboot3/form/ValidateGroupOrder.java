package com.example.springboot3.form;

import javax.validation.GroupSequence;

@GroupSequence({ValidateGroupRequired.class, ValidateGroupContents.class})
public interface ValidateGroupOrder {
}
