package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.config.CouchbaseConfig;
import com.amdocs.coe_dashboard.models.Admin;
import com.amdocs.coe_dashboard.models.AdminWrapper;
import com.amdocs.coe_dashboard.models.Employee;
import com.amdocs.coe_dashboard.models.EmployeeWrapper;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryScanConsistency;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final Cluster cluster;
    private final Collection employeeCol;
    private final CouchbaseConfig couchbaseConfig;


    public AdminRepositoryImpl(Cluster cluster, Bucket bucket, CouchbaseConfig couchbaseConfig) {
        this.cluster = cluster;
        this.employeeCol = bucket.scope("dashboard").collection("employee");
        this.couchbaseConfig = couchbaseConfig;
    }
    @Override
    public boolean adminLogin(String email, String passwd) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`admin` WHERE adminEmail = $1 AND adminPassword = $2";
        QueryResult result =cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(email, passwd))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS));
        System.out.println(result.rowsAs(AdminWrapper.class));
        return !result.rowsAs(AdminWrapper.class).isEmpty();

    }

    @Override
    public List<Employee> findByIdOrName(String input) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` WHERE empId LIKE '%' || $1 || '%' OR empName LIKE '%' || $1 || '%'";
        List<Employee> result = new ArrayList<>();
        cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(input, input))
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
    public Employee getEmpById(String id) {
        return employeeCol.get(id).contentAs(Employee.class);
    }
}
