// package com.btbox.common.translation.impl;
//
// import com.btbox.common.annotation.TranslationType;
// import com.btbox.common.translation.TranslationInterface;
// import lombok.AllArgsConstructor;
// import org.springframework.stereotype.Component;
//
// /**
//  * 用户名翻译实现
//  *
//  * @author Lion Li
//  */
// @Component
// @AllArgsConstructor
// @TranslationType(type = TransConstant.USER_ID_TO_NAME)
// public class UserNameTranslationImpl implements TranslationInterface<String> {
//
//     private final UserService userService;
//
//     @Override
//     public String translation(Object key, String other) {
//         if (key instanceof Long) {
//             return userService.selectUserNameById((Long) key);
//         }
//         return null;
//     }
// }
