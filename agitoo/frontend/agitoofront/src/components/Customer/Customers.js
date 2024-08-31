import React, { useEffect, useState } from 'react';

function Customers() {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/customer/get-all')
      .then(response => response.json())
      .then(data => setCustomers(data))
      .catch(error => console.error('Error fetching customers:', error));
  }, []);

  return (
    <div>
      <h2>Customers</h2>
      <ul>
        {customers.map(customer => (
          <li key={customer.customerId}>{customer.customerType}</li>
        ))}
      </ul>
    </div>
  );
}

export default Customers;