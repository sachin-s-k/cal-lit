package com.example.cal_lit_backend.migration;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@ChangeUnit(
        id="001-add-role-field",
        order="001",
        author = "sachin"

)
public class V001_AddRoleField{
    @Execution
    public void execute(MongoTemplate mongoTemplate){
        System.out.println("Migration running...-----------------------------");

        Query query= Query.query(
                Criteria.where("role").exists(false)
        );
        Update update =new Update().set("role","USER");
        mongoTemplate.updateMulti(query,update,"users");

    }
    @RollbackExecution
    public void rollback(MongoTemplate mongoTemplate) {

        mongoTemplate.updateMulti(
                new Query(),
                new Update().unset("role"),
                "users"
        );
    }

}