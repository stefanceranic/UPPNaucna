naucnaCentrala.controller('prijavaRada', [
		'$rootScope','$scope',
		'$state',
		'$http',
		'$stateParams',
		function($rootScope, $scope, $state, $http, $stateParams) {

	$scope.processId=$stateParams.processId;
			$scope.model = {};
			$scope.properties = [];
			$scope.pokreniProccess = function() {
				$http.get("http://localhost:8080/process/getFF/"+$scope.processId)
					.then(
						function(response) {
							$scope.properties = response.data;

						}, function(response) {
						});
			}
			$scope.pokreniProccess();


            $scope.submit=function(){
                var o = [];
                for (var m in $scope.model) {
                    console.log(m + "  -  " + $scope.model[m]);
                    o.push({ "fieldId": m, "fieldValue": $scope.model[m] });
                }
                var x = document.getElementById("fileMedia");
                o.push({"fieldId" : "putanjaFajla", "fieldValue": x.files[0].name});
                o.push({"fieldId" : "autor", "fieldValue": $rootScope.korisnik.username});
                console.log(o);
                $http.post("http://localhost:8080/rad/prijavi/"+$scope.properties.taskId, o)
                    .then(function (response) {
                            alert("Uspesno ste prijavili rad")
                            $state.go("pocetnaAutor");
                        }
                        , function (response) {
                            alert("Neuspeh");
                        });

            }
		} ]);