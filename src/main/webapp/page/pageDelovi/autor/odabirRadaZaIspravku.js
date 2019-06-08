naucnaCentrala.controller('odabirRadaZaIspravku', [
		'$rootScope',
		'$scope',
		'$state',
		'$http',
		'FileSaver',
		'Blob',
		function($rootScope, $scope, $state, $http, FileSaver, Blob) {

			$scope.taskovi = [];

			$scope.prikaziCasopise = function() {
				$http.get(
						"http://localhost:8080/process/taskoviZaIspravak/"
								+ $rootScope.korisnik.email).then(
						function(response) {
							$scope.taskovi = response.data;

						}, function(response) {
						});
			};

			$scope.odaberiCasopis = function(x) {
				$state.go("urednikPregledRada", {
					"rad" : x.rad,
					"task" : x.taskId,
					"processInstanceId" : x.processId
				});
			};
			
			$scope.preuzmiRad = function(y) {
				
				$http.post("http://localhost:8080/rad/downloadNaziv",y.rad, {responseType: 'arraybuffer'}).then(
						function(response) {
							var myData = new Blob([response.data], { type: 'application/pdf;charset=utf-8' });
			                FileSaver.saveAs(myData, 'nesto.pdf');
						}, function(response){
							
						});
				
			};
			
			$scope.posaljiRad = function(task) {
				var putanja = document.getElementById("fileMedia").files[0].name;
                $http.post("http://localhost:8080/rad/ispraviRad/"+putanja,task).then(
                    function(response) {
							alert("Uspesno ste ispravili rad!");
							$state.go("pocetnaAutor");
                    }, function(response){

                        alert("Greska");
                    });
				
			}
			
			$scope.prikaziCasopise();

		} ]);