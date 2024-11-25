package com.amdocs.coe_dashboard.repositories;

//import org.couchbase.quickstart.springboot.configs.CouchbaseConfig;
import com.amdocs.coe_dashboard.config.CouchbaseConfig;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.models.EmployeeWrapper;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{

    private final Cluster cluster;
    private final Collection employeeCol;
    private final CouchbaseConfig couchbaseConfig;


    public EmployeeRepositoryImpl(Cluster cluster, Bucket bucket, CouchbaseConfig couchbaseConfig) {
        this.cluster = cluster;
        this.employeeCol = bucket.scope("dashboard").collection("employee");
        this.couchbaseConfig = couchbaseConfig;
    }


    @Override
    public Optional<Employee> employeeLogin(String email, String passwd) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` WHERE empEmail = $1 AND empPasswd = $2";
        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(email, passwd))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(EmployeeWrapper.class).forEach(e -> result.add(e.getEmployee()));

        // Return the first employee found or empty if no employee is found
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }


    @Override
    public List<Employee> findByIdOrName(String input) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` "
                + "WHERE LOWER(empId) LIKE '%' || LOWER($1) || '%' "
                + "OR LOWER(empEmail) LIKE '%' || LOWER($1) || '%' "
                + "OR LOWER(empName) LIKE '%' || LOWER($1) || '%'";

        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(input))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(EmployeeWrapper.class).forEach(e -> result.add(e.getEmployee()));
        return result;
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

    @Override
    public List<Employee> getAllEmp() {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` ";

        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions()
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(EmployeeWrapper.class).forEach(e->result.add(e.getEmployee()));
        return result;
    }
}
