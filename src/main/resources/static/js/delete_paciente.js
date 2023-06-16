function deleteBy(id) {
          const url = '/pacientes'+"/"+id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => console.log(data))
          .catch(error => {
              alert("Error: " + error);
          })

          let table = document.getElementById("´pacienteTable");
          table.deleteRow(id);
}