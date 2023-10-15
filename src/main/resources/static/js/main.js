var app = angular.module('app', []);

app.controller('mainController', function ($scope, $http) {

    const csrfHeader = {};
    csrfHeader[document.querySelector("meta[name='csrf-header']").content]
        = document.querySelector('meta[name="csrf-token"]').content;

    console.log(csrfHeader);

    $scope.labs = [];

    marks = [];

    $scope.mark = 0;

    $scope.students = [];
    
    this.getMarks = function() {
        $http.get('/marks_table/marks').then(this.successGetMarks, this.standartHandleError);
    };

    this.addStudent = function(studentName) {
        $http.post('/marks_table/api/students', { id: 0, name: studentName }, { headers: csrfHeader } ).then(this.successAddStudent, this.standartHandleError);
    };

    this.deleteStudent = function(student) {
        $scope.deletedStudentId = student;
        $http.delete('/marks_table/api/students', {params: { student_id: student }, headers: csrfHeader }).then(this.successDeleteStudent, this.standartHandleError);
    };

    this.changeMark = function(student, numberLab, mark) {
        $scope.markedStudent = student;
        $scope.markedLabNum = numberLab;
        $scope.changedMarkValue = mark;

        $http.put('/marks_table/api/marks', {
            mark_pk: {
                student_id: student,
                lab_num: numberLab
            }, value: mark}, { headers: csrfHeader } ).then(this.successChangeMark, this.standartHandleError);
    };

    this.addLabWork = function() {
        $http.post('/marks_table/api/lab_work', { id: 0, lab_num: $scope.labs.length + 1 }, { headers: csrfHeader } ).then(this.successAddLabWork, this.standartHandleError);
    };

    this.deleteLabWork = function() {
        $http.delete('/marks_table/api/lab_work', { params: { lab_num: $scope.labs.length }, headers: csrfHeader }).then(this.successDeleteLabWork, this.standartHandleError);
    };

    this.getMark = function(studentId, labNum) {
        return $scope.mark = marks.filter((m) => m.studentId === studentId)
                                  .filter((m) => m.labNum === labNum)[0].mark;
    }

    this.successGetMarks = function(response) {
        marksList = response.data;
    
        marksList.forEach(item => {
            const student = $scope.students.find(s => s.value === item.student_id);
            if (!student) {
                $scope.students.push({ value: item.student_id, label: item.student_name });
            }
            
            const lab = $scope.labs.find(l => l.value === item.lab_num);
            if (!lab) {
                $scope.labs.push({ value: item.lab_num, label: 'ЛР' + item.lab_num })
            }

            marks.push( {studentId: item.student_id, labNum: item.lab_num, mark: item.value} );
        });
    }

    this.successAddStudent = function(response) {
        let addedStudent = response.data;

        $scope.students.push({ value: addedStudent.id, label: addedStudent.name });

        for (lab of $scope.labs) {
            marks.push( {studentId: addedStudent.id, labNum: lab.value, mark: 0} )
        }
    }

    this.successDeleteStudent = function(response) {
        for (var i = 0; i < $scope.students.length; i++) {
            if ($scope.students[i].value === $scope.deletedStudentId) {
                $scope.students.splice(i, 1);
                break;
            }
        }
    }

    this.successAddLabWork = function(response) {
        let newLabNum = $scope.labs.length + 1;
        $scope.labs.push({ value: newLabNum, label: 'ЛР' + newLabNum });

        for (student of $scope.students) {
            marks.push( {studentId: student.value, labNum: newLabNum, mark: 0} );
        }
    }

    this.successDeleteLabWork = function(response) {
        let deletedLabNum = $scope.labs.length;
        $scope.labs.pop();

        marks = marks.filter((m) => m.labNum !== deletedLabNum);
        console.log(marks);
    }

    this.successChangeMark = function(response) {
        for (m of marks) {
            if (m.studentId ===  $scope.markedStudent && m.labNum ===  $scope.markedLabNum) {
                m.mark = $scope.changedMarkValue;
            }
        }
    }

    this.standartHandleError = function(error) {
        console.log(error);
    }
})