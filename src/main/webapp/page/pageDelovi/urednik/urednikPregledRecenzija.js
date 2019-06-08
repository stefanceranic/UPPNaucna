naucnaCentrala.controller('urednikPregledRecenzija', [
		'$rootScope', '$scope',
		'$state',
		'$http',
		'$stateParams',
		function($rootScope, $scope, $state, $http, $stateParams) {

			 $scope.model = {};

			var rad = $stateParams.rad;
			var task = $stateParams.task;
			var processInstanceId = $stateParams.processInstanceId;

			$scope.recenzije = {};

		    $scope.logout = function() {
		    	$rootScope.korisnik = "";
		    	$state.go("login"); 	
		    }
			
			$scope.pokreniProccess = function() {
                $http.get("http://localhost:8080/process/getFF/"+processInstanceId)
                    .then(
                        function(response) {
                            $scope.properties = response.data;

                        }, function(response) {
                        });
            }
            $scope.pokreniProccess();
			
			$scope.vratiRecenzije = function() {
				$http
						.get(
								"http://localhost:8080/recenzija/vratiRecenzije/"
										+ rad).then(function(response) {
							$scope.recenzije = response.data;

						}, function(response) {
						});
			};

			$scope.vratiRecenzije();
			
			 $scope.potvrdiOcenu=function(){
	                var o = [];
	                for (var m in $scope.model) {
	                    console.log(m + "  -  " + $scope.model[m]);
	                    o.push({ "fieldId": m, "fieldValue": $scope.model[m] });
	                }
	                $http.post("http://localhost:8080/urednik/oceni/"+$scope.properties.taskId, o)
	                    .then(function (response) {
	                            alert("Uspesno ste ocenili rad")
	                            $state.go("pocetnaUrednik");
	                        }
	                        , function (response) {
	                            alert("Neuspeh");
	                        });

	            }
			 
			 $scope.preuzmiRad = function(y) {
					
					$http.post("http://localhost:8080/rad/downloadNaziv",y, {responseType: 'arraybuffer'}).then(
							function(response) {
								var myData = new Blob([response.data], { type: 'application/pdf;charset=utf-8' });
				                FileSaver.saveAs(myData, 'nesto.pdf');
							}, function(response){
								
							});
					
				};

		} ]);