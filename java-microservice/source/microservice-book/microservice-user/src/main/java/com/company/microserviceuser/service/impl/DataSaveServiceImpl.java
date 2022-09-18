// package com.company.microserviceuser.service.impl;

// import com.company.microserviceuser.service.*;
// import com.company.microserviceuser.mapper.*;
// import com.company.microserviceuser.dto.*;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.transaction.interceptor.TransactionAspectSupport;

// import java.util.Map;
// import java.util.HashMap;

// /**
//  * @author xindaqi
//  * @since 2020-10-26
//  */

// @Service
// @Transactional
// public class DataSaveServiceImpl implements IDataSaveService{

//     @Autowired 
//     private QuestionsMapper questionsMapper;

//     @Autowired
//     private AnswersMapper answersMapper;

//     @Override 
//     public Boolean saveQuestionAndAnswerWithTransactionRollback(QuestionAnswerInputDTO params) {

//         Map<String, String> questionsMap = new HashMap<>();
//         questionsMap.put("questions", params.getQuestions());
//         Integer addQuestionFlag = questionsMapper.addQuestions(questionsMap);

//         // Integer errorFlag = 1/0;
//         Map<String, String> answersMap = new HashMap<>();
//         answersMap.put("answers", params.getAnswers());
//         answersMap.put("questions", params.getQuestions());
//         Integer addAnswerFlag = answersMapper.addAnswers(answersMap);
//         return true;

        
//     }

//     @Override 
//     public Boolean saveQuestionAndAnswerWithTryCatchWithoutRollback(QuestionAnswerInputDTO params) {
//         try {
//             Map<String, String> questionsMap = new HashMap<>();
//             questionsMap.put("questions", params.getQuestions());
//             Integer addQuestionFlag = questionsMapper.addQuestions(questionsMap);

//             Integer errorFlag = 1/0;
//             Map<String, String> answersMap = new HashMap<>();
//             answersMap.put("answers", params.getAnswers());
//             answersMap.put("questions", params.getQuestions());
//             Integer addAnswerFlag = answersMapper.addAnswers(answersMap);
//             return true;

//         }catch(Exception e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     @Override
//     @Transactional(rollbackFor = Exception.class)
//     public Boolean saveQuestionAndAnswerWithTryCatchWithRollback(QuestionAnswerInputDTO params) {
//         try {
//             Map<String, String> questionsMap = new HashMap<>();
//             questionsMap.put("questions", params.getQuestions());
//             Integer addQuestionFlag = questionsMapper.addQuestions(questionsMap);

//             Integer errorFlag = 1/0;
//             Map<String, String> answersMap = new HashMap<>();
//             answersMap.put("answers", params.getAnswers());
//             answersMap.put("questions", params.getQuestions());
//             Integer addAnswerFlag = answersMapper.addAnswers(answersMap);
//             return true;

//         }catch(Exception e) {
//             e.printStackTrace();
//             TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//             return false;
//         }
//     }
    
// }