package com.amdocs.coe_dashboard.repositories;

//import org.couchbase.quickstart.springboot.configs.CouchbaseConfig;
import com.amdocs.coe_dashboard.config.CouchbaseConfig;
import com.amdocs.coe_dashboard.models.Employee;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryScanConsistency;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    private final Cluster cluster;
    private final Collection employeeCol;
    private final CouchbaseConfig couchbaseConfig;


    public EmployeeRepositoryImpl(Cluster cluster, Collection employeeCol, CouchbaseConfig couchbaseConfig) {
        this.cluster = cluster;
        this.employeeCol = employeeCol;
        this.couchbaseConfig = couchbaseConfig;
    }


    @Override
    public boolean employeeLogin(String email, String passwd) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` WHERE email = $1 AND passwd = $2";
        return !cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(email, passwd))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(Employee.class).isEmpty();
    }

    @Override
    public List<Employee> findByIdOrName(String input) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` WHERE id = $1 OR name = $1";
        return cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(input))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(Employee.class);
    }

    @Override
    public Employee addEmp(String id, Employee employee) {
        employeeCol.insert(id, employee);
        return employee;
    }

    @Override
    public Employee update(String id, Employee employee) {
        employeeCol.replace(id, employee);
        return employee;
    }

    @Override
    public Employee getEmpById(String id)
    {
         return employeeCol.get(id).contentAs(Employee.class);
    }
}
