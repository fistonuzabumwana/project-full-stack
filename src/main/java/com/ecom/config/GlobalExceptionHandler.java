package com.ecom.config;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Handler for NoResourceFoundException (static resource not found)
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResourceFoundException(NoResourceFoundException ex, Model model) {
        logger.warn("No static resource found: {}", ex.getResourcePath());
        ModelAndView mav = new ModelAndView("error/404");
        model.addAttribute("errorTitle", "Resource Not Found");
        model.addAttribute("errorMessage", "The requested resource could not be found: " + ex.getResourcePath());
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }

    // Handler for 404 - Page Not Found
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, Model model) {
        logger.warn("404 - Page not found: {}", ex.getRequestURL());
        ModelAndView mav = new ModelAndView("error/404");
        model.addAttribute("errorTitle", "Page Not Found");
        model.addAttribute("errorMessage", "The page you requested could not be found.");
        model.addAttribute("path", ex.getRequestURL());
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }

    // Handler for EntityNotFoundException (e.g., when a database entity is not found)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex, Model model) {
        logger.warn("Entity not found: {}", ex.getMessage());
        ModelAndView mav = new ModelAndView("error/404");
        model.addAttribute("errorTitle", "Resource Not Found");
        model.addAttribute("errorMessage", "The requested item could not be found: " + (ex.getMessage() != null ? ex.getMessage() : "Unknown resource"));
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }

    // Handler for IllegalArgumentException (e.g., invalid request parameters)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        logger.warn("Illegal argument: {}", ex.getMessage());
        ModelAndView mav = new ModelAndView("error/400");
        model.addAttribute("errorTitle", "Invalid Request");
        model.addAttribute("errorMessage", "There was a problem with your request: " + (ex.getMessage() != null ? ex.getMessage() : "Invalid input"));
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }

    // Handler for Thymeleaf TemplateInputException (e.g., parsing errors in templates)
    @ExceptionHandler(TemplateInputException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleTemplateInputException(TemplateInputException ex, Model model) {
        logger.error("Thymeleaf template parsing error: {}", ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("error/500");
        model.addAttribute("errorTitle", "Template Error");
        model.addAttribute("errorMessage", "There was an issue rendering the page. Please contact support if this persists.");
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }

    // Catch-all for other unexpected exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleAllOtherExceptions(Exception ex, Model model) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        ModelAndView mav = new ModelAndView("error/500");
        model.addAttribute("errorTitle", "Internal Server Error");
        model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later or contact support.");
        model.addAttribute("timestamp", LocalDateTime.now().format(formatter));
        return mav;
    }
}