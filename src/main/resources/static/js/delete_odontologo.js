
    function deleteBy(id) {
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => console.log(data))
          .catch(error => {
              alert("Error: " + error);
          })

          let table = document.getElementById("odontologoTable");
          table.deleteRow(id);
}
