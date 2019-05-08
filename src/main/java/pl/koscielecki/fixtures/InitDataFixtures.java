package pl.koscielecki.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDataFixtures {

    @Autowired
    RoleFixtures roleFixtures;

    @PostConstruct
    public void initData(){
        roleFixtures.crateAndInsertIntoDb();
    }
}
