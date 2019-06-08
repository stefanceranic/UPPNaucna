naucnaCentrala.controller('urednikPregledRada', [
		'$rootScope', '$scope',
		'$state',
		'$http',
		'$stateParams',
		function($rootScope, $scope, $state, $http, $stateParams) {

			var rad = $stateParams.rad;
			var task = $stateParams.task;
			var processInstanceId = $stateParams.processInstanceId;
			
			$scope.rad = {};

		    $scope.logout = function() {
		    	$rootScope.korisnik = "";
		    	$state.go("login"); 	
		    }
			
			$scope.vratiRad = function() {
				$http.get("http://localhost:8080/rad/vratiRad/" + rad).then(
						function(response) {
							$scope.rad = response.data;

						}, function(response) {
						});
			};

			$scope.proveraRada = function(x) {
				$http.post("http://localhost:8080/urednik/proveraRada/"+task, x)
						.then(function(response) {
							if(x)
								$state.go("urednikPregledFormataRada", {"rad": rad, "processInstanceId": processInstanceId});
							else
								$state.go("pocetnaUrednik");
						}, function(response) {
						});

			}

			$scope.vratiRad();

		} ]);